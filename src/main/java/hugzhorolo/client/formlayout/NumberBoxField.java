package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NumberBoxField extends FormField {

  private TextBox textBox = new TextBox();
  
  @Override
  public void setFieldConfig(FieldConfig fieldConfig) {
  }

  @Override
  public Widget asWidget() {
    return textBox;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Void> handler) {
    return textBox.addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        handler.onValueChange(null);
      }
    });
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    textBox.fireEvent(event);
  }

  @Override
  public String getValue() {
    return new JSONNumber(Double.parseDouble(textBox.getText())).toString();
  }
  
  @Override
  public JSONValue getJsonValue() {
    return new JSONNumber(Double.parseDouble(textBox.getText()));
  }

  @Override
  public void setVisible(boolean isVisible) {
    textBox.setVisible(isVisible);
  }

  @Override
  public void setValue(String key, JSONValue value, JSONValue formData) {
    textBox.setText("" + value.isNumber().doubleValue());
  }


  @Override
  public void setValue(String valueJson) {
    textBox.setText("" + JSONParser.parseStrict(valueJson).isNumber().doubleValue());
  }

}
