package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxField extends FormField {

  public static class TextBoxConfig {

    private int maxLength;

    public TextBoxConfig() {
    }

    public TextBoxConfig maxLength(int maxLength) {
      this.maxLength = maxLength;
      return this;
    }

    public int getMaxLength() {
      return maxLength;
    }
  }

  private TextBoxConfig specConfig;
  private TextBox textBox = new TextBox();
  private FormLayoutStyle style = Res.INST.get().style();

  public TextBoxField() {
    textBox.addStyleName(style.TextBoxField());
    textBox.addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        ValueChangeEvent.fire(TextBoxField.this, null);
      }
    });
  }

  @Override
  public void onFieldConfigSet() {
    if (fieldConfig.getFormFieldSpecificConfig() != null) {
      specConfig = (TextBoxConfig) fieldConfig.getFormFieldSpecificConfig();
    }

  }

  @Override
  public Widget asWidget() {
    return textBox;
  }

  @Override
  public String getValue() {
    return new JSONString(textBox.getValue()).toString();
  }

  @Override
  public JSONValue getJsonValue() {
    return new JSONString(textBox.getValue());
  }

  @Override
  public void setValue(String valueJson) {
    if (valueJson != null) {
      textBox.setText(JSONParser.parseStrict(valueJson).isString().stringValue());
    }
  }

  @Override
  public void setValue(JSONValue value, JSONValue formData) {
    if (value != null) {
      textBox.setText(value.isString().stringValue());
    }
  }

}
