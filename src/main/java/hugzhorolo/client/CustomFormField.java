package hugzhorolo.client;

import hugzhorolo.client.formlayout.FormField;

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
