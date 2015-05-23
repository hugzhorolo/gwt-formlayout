package hugzhorolo.client;

import hugzhorolo.client.formlayout.FieldConfig;
import hugzhorolo.client.formlayout.FormConfig;
import hugzhorolo.client.formlayout.FormField;
import hugzhorolo.client.formlayout.FormLayout;
import hugzhorolo.client.formlayout.ListBoxField;
import hugzhorolo.client.formlayout.ListBoxField.ListBoxConfig;
import hugzhorolo.client.formlayout.TextBoxField.TextBoxConfig;

import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint {

  private DemoStyle style = Res.INST.get().style();

  @Override
  public void onModuleLoad() {
    GWT.setUncaughtExceptionHandler(new SuperDevModeUncaughtExceptionHandler());
    try {
      helloWorld();
      basicConfiguration();
    } catch (Exception e) {
      log(e);
    }
  }

  private native void log(Throwable t) /*-{
		var logError = console.error || console.log;
		var backingError = t.__gwt$backingJsError;
		logError.call(console, backingError && backingError.stack);
  }-*/;

  private void helloWorld() throws Exception {
    JSONObject sampleData = new JSONObject();
    sampleData.put("Field1", new JSONNumber(1.0));
    sampleData.put("Field2", new JSONString("Hello world"));
    sampleData.put("Field3", JSONBoolean.getInstance(true));

    // Sample 1 - basic usage
    final FormLayout formLayout = new FormLayout(sampleData.toString());
    RootPanel.get().add(formLayout);

    Button button = new Button("Apply");
    button.addStyleName(style.apply());
    button.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Window.alert(formLayout.getDataJson());
      }
    });

    formLayout.appendWidgetToBottom(button);
  }

  private void basicConfiguration() throws Exception {
    JSONObject sampleData = new JSONObject();
    sampleData.put("Field1", new JSONNumber(1.0));
    sampleData.put("Field2", new JSONString("Hello world"));
    sampleData.put("Field3", JSONBoolean.getInstance(true));
    sampleData.put("Field4", new JSONString("Item 2"));
    sampleData.put("Field5", new JSONNumber(1.0));

    final FormLayout formLayout =
      new FormLayout(
        sampleData.toString(),
        new FormConfig()
          .addFieldConfig(
            new FieldConfig("Field1").setFieldWidget(new CustomFormField()).setFieldDisplayName(
              "Field1 display name"))
          .addFieldConfig(
            new FieldConfig("Field2").setSpecificConfig(new TextBoxConfig().maxLength(3)))
          .addFieldConfig(
            new FieldConfig("Field4").setFieldWidget(new ListBoxField()).setSpecificConfig(
              new ListBoxConfig().stringValues(
                "Item 1",
                "Item 2",
                "Item 3")))
      );

    formLayout.addValueChangeHandler("Field3", new ValueChangeHandler<Void>() {

      @Override
      public void onValueChange(ValueChangeEvent<Void> event) {
        try {
          boolean field3Val =
            JSONParser.parseStrict(formLayout.getDataJson()).isObject().get("Field3").isBoolean().booleanValue();
          formLayout.setFieldVisible("Field1", field3Val);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    formLayout.setValueChangeHandler(new ValueChangeHandler<Void>() {

      @Override
      public void onValueChange(ValueChangeEvent<Void> event) {
        Window.alert("Form item changed: "
          + ((FormField) event.getSource()).getFieldConfig().getFieldName()
          + ", new value: " + ((FormField) event.getSource()).getValue());
      }
    });
    RootPanel.get().add(formLayout);

    Button button = new Button("Apply");
    button.addStyleName(style.apply());
    button.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        System.out.println(formLayout.getDataJson());
      }
    });


    Button updateValueRangeButton = new Button("Update value range of listbox");
    updateValueRangeButton.addStyleName(style.apply());
    updateValueRangeButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Map<String, String> newValueRange = new TreeMap<String, String>();
        newValueRange.put("newItem1Key", "New Item 1");
        newValueRange.put("newItem2Key", "New Item 2");
        formLayout.getListBoxField("Field4").updateValueRange(newValueRange);
      }
    });

    formLayout.appendWidgetToBottom(button);
    formLayout.appendWidgetToBottom(updateValueRangeButton);
  }
}
