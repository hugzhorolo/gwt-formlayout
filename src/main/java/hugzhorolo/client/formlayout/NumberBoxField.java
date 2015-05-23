package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

// TODO: Validation, parsing
public class NumberBoxField extends FormField {

  private TextBox textBox = new TextBox();

  private FormLayoutStyle style = Res.INST.get().style();

  public NumberBoxField() {
    textBox.addStyleName(style.TextBoxField());
    textBox.addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        ValueChangeEvent.fire(NumberBoxField.this, null);
      }
    });
  }

  @Override
  public void onFieldConfigSet() {
  }

  @Override
  public Widget asWidget() {
    return textBox;
  }

  @Override
  public JSONValue getJsonValue() {
    return new JSONNumber(Double.parseDouble(textBox.getText()));
  }

  @Override
  public void setValue(JSONValue value, JSONObject formData) {
    textBox.setText("" + value.isNumber().doubleValue());
  }


  @Override
  public void setValue(String valueJson) {
    textBox.setText("" + JSONParser.parseStrict(valueJson).isNumber().doubleValue());
  }

}
