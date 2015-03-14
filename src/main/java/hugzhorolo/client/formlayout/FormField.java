package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.IsWidget;

public abstract class FormField implements IsWidget, HasValueChangeHandlers<Void> {

	public abstract void setFieldConfig(FieldConfig fieldConfig);
	
	abstract void setValue(String key, JSONValue value, JSONValue formData);
	
	abstract JSONValue getJsonValue();
	
	public abstract void setValue(String valueJson);
	
	public abstract String getValue();
	
	public abstract void setVisible(boolean isVisible);
	

}
