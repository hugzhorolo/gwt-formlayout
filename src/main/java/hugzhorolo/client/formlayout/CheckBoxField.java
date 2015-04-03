package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;

public class CheckBoxField extends FormField {

  private CheckBox checkBox = new CheckBox();

  @Override
  public void onFieldConfigSet() {
  }
  
  @Override
  public Widget asWidget() {
    return checkBox;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Void> handler) {    
    return checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

      @Override
      public void onValueChange(ValueChangeEvent<Boolean> event) {
        handler.onValueChange(null);
      }
    });
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    checkBox.fireEvent(event);
  }

  @Override
  public String getValue() {
    return JSONBoolean.getInstance(checkBox.getValue()).toString();
  }


  @Override
  public JSONValue getJsonValue() {
    return JSONBoolean.getInstance(checkBox.getValue());
  }

  @Override
  public void setVisible(boolean isVisible) {
    checkBox.setVisible(isVisible);
  }


  @Override
  public void setValue(String key, JSONValue value, JSONValue formData) {
    if (value != null){
      checkBox.setValue(value.isBoolean().booleanValue());
    }
    
  }

  @Override
  public void setValue(String valueJson) {
    if (valueJson != null){
      checkBox.setValue(JSONParser.parseStrict(valueJson).isBoolean().booleanValue());
    }
  }

}
