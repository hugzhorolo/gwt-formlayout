package hugzhorolo.client.formlayout;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Widget;

public class CheckBoxField extends FormField {

  private FocusPanel panel = new FocusPanel();
  private CheckBox checkBox = new CheckBox();

  private FormLayoutStyle style = Res.INST.get().style();

  public CheckBoxField() {
    panel.addStyleName(style.CheckBoxField());
    panel.add(checkBox);

    panel.addKeyUpHandler(new KeyUpHandler() {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
          checkBox.setValue(!checkBox.getValue(), true);
        }
      }
    });

    checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        ValueChangeEvent.fire(CheckBoxField.this, null);
      }
    });
  }

  @Override
  public void onFieldConfigSet() {
  }

  @Override
  public Widget asWidget() {
    return panel;
  }


  @Override
  public JSONValue getJsonValue() {
    return JSONBoolean.getInstance(checkBox.getValue());
  }

  @Override
  public void setValue(JSONValue value, JSONObject formData) {
    if (value != null) {
      checkBox.setValue(value.isBoolean().booleanValue());
    }

  }

  @Override
  public void setValue(String valueJson) {
    if (valueJson != null) {
      checkBox.setValue(JSONParser.parseStrict(valueJson).isBoolean().booleanValue());
    }
  }

}
