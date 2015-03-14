package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxField extends FormField {

	public static class TextBoxConfigBuilder {		
		
		public TextBoxConfigBuilder() {
		}
		
		public TextBoxConfigBuilder maxLength(int lenght){
			//todo;
			return this;
		}
		
		public String build(){
			return null;
		}
	}

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
	void setValue(JSONValue value, JSONValue formData) {
	}

	@Override
	public void setValue(String valueJson) {
	}

}
