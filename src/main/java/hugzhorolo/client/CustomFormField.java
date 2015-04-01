package hugzhorolo.client;

import hugzhorolo.client.formlayout.FieldConfig;
import hugzhorolo.client.formlayout.FormField;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

public class CustomFormField extends FormField {


  @Override
  public void setFieldConfig(FieldConfig fieldConfig) {
  }

  @Override
  public Widget asWidget() {
    return null;
  }

  @Override
  public String getValue() {
    return null;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Void> handler) {
    return null;
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
  }

  @Override
  public void setVisible(boolean isVisible) {
  }

  @Override
  public void setValue(String key, JSONValue value, JSONValue formData) {
  }

  @Override
  public JSONValue getJsonValue() {
    return null;
  }

  @Override
  public void setValue(String valueJson) {
  }

}
