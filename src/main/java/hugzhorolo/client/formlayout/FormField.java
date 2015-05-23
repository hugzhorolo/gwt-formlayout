package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.IsWidget;

// TODO: too many setValue
public abstract class FormField implements IsWidget, HasValueChangeHandlers<Void> {

  protected EventBus bus = new SimpleEventBus();
  protected FieldConfig fieldConfig;
  protected FormLayout form;

  void setFormLayout(FormLayout form) {
    this.form = form;
  }

  public FormLayout getForm() {
    return form;
  }

  public void setFieldConfig(FieldConfig fieldConfig) {
    this.fieldConfig = fieldConfig;
    onFieldConfigSet();
  }

  public FieldConfig getFieldConfig() {
    return fieldConfig;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Void> handler) {
    return bus.addHandler(ValueChangeEvent.getType(), handler);
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    bus.fireEventFromSource(event, this);
  }

  public abstract void onFieldConfigSet();

  public abstract void setValue(JSONValue value, JSONObject formData);

  public abstract JSONValue getJsonValue();

  public abstract void setValue(String valueJson);

  public String getValue() {
    return getJsonValue().toString();
  }

}
