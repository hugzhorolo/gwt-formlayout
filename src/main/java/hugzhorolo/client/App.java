package hugzhorolo.client;

import hugzhorolo.client.formlayout.CustomFormField;
import hugzhorolo.client.formlayout.FieldConfig;
import hugzhorolo.client.formlayout.FormLayout;
import hugzhorolo.client.formlayout.FormConfig;
import hugzhorolo.client.formlayout.TextBoxField;
import hugzhorolo.client.formlayout.TextBoxField.TextBoxConfigBuilder;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class App implements EntryPoint {

	@Override
	public void onModuleLoad() {
		try {
			helloWorld();
			basicConfiguration();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void helloWorld() throws Exception {
		JSONObject sampleData = new JSONObject();
		sampleData.put("Field 1", new JSONNumber(1.0));
		sampleData.put("Field 2", new JSONString("Hello world"));
		sampleData.put("Field 3", JSONBoolean.getInstance(true));

		// Sample 1 - basic usage
		final FormLayout formLayout = new FormLayout(sampleData.toString());
		RootPanel.get().add(formLayout);

		Button button = new Button("Finish editing");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(formLayout.getDataJson());
			}
		});

		RootPanel.get().add(button);
	}

	private void basicConfiguration() throws Exception {
		JSONObject sampleData = new JSONObject();
		sampleData.put("Field 1", new JSONNumber(1.0));
		sampleData.put("Field 2", new JSONString("Hello world"));
		sampleData.put("Field 3", JSONBoolean.getInstance(true));

		final FormLayout formLayout = new FormLayout(sampleData.toString(), new FormConfig().addFieldConfig(
				new FieldConfig("Field 1").setFieldWidget(new CustomFormField())).addFieldConfig(
				new FieldConfig("Field 2").setSpecificConfig(new TextBoxConfigBuilder().maxLength(3).build())));

		formLayout.addValueChangeHandler("Field 3", new ValueChangeHandler<Void>() {

			@Override
			public void onValueChange(ValueChangeEvent<Void> event) {
				try {
					boolean field3Val = JSONParser
							.parseStrict(formLayout.getDataJson())
							.isObject()
							.get("Field 3")
							.isBoolean()
							.booleanValue();
					formLayout.setFieldVisible("Field 1", field3Val);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		RootPanel.get().add(formLayout);

		Button button = new Button("Finish editing");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println(formLayout.getDataJson());
			}
		});

		RootPanel.get().add(button);
	}
}
