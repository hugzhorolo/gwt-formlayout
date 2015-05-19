package hugzhorolo.client;

import hugzhorolo.client.formlayout.FormField;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class CustomFormField extends FormField {

  private Label label = new Label("Your customized field");

  @Override
  public void onFieldConfigSet() {
  }

  @Override
  public Widget asWidget() {
    return label;
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
  public void setValue(JSONValue value, JSONValue formData) {
  }

  @Override
  public JSONValue getJsonValue() {
    return null;
  }

  @Override
  public void setValue(String valueJson) {
  }

}
