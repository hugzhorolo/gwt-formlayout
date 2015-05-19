package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.IsWidget;

public abstract class FormField implements IsWidget, HasValueChangeHandlers<Void> {

  protected FieldConfig fieldConfig;

  public void setFieldConfig(FieldConfig fieldConfig) {
    this.fieldConfig = fieldConfig;
    onFieldConfigSet();
  }

  public FieldConfig getFieldConfig() {
    return fieldConfig;
  }

  public abstract void onFieldConfigSet();

  public abstract void setValue(JSONValue value, JSONValue formData);

  public abstract JSONValue getJsonValue();

  public abstract void setValue(String valueJson);

  public abstract String getValue();


}
