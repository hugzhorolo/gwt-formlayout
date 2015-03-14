package hugzhorolo.client.formlayout;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.FlowPanel;

public class FormLayout extends FlowPanel {

	private JSONObject originalData;
	private FormConfig config;
	private List<String> fieldOrder;
	private List<FormField> fields = new ArrayList<FormField>();

	public FormLayout(String dataJson) throws Exception {
		this(dataJson, new FormConfig());
	}

	public FormLayout(String dataJson, FormConfig config) throws Exception {
		this(JSONParser.parseLenient(dataJson).isObject(), config);
	}

	public FormLayout(JSONObject dataJson, FormConfig config) throws Exception {
		originalData = dataJson;
		calcFieldOrder();
		renderFields();
	}

	private void calcFieldOrder() {
		fieldOrder = new ArrayList<String>();
		for (String key : originalData.keySet()) {
			fieldOrder.add(key);
		}
	}

	private void renderFields() throws Exception {
		for (String key : fieldOrder) {
			FormField field = createField(key);
			add(field);
		}
	}

	private FormField createField(String key) throws Exception {
		FieldConfig fieldConfig = config.getFieldConfigs(key);

		FormField field = null;
		if (fieldConfig != null && fieldConfig.getFieldWidget() != null) {
			field = fieldConfig.getFieldWidget();
		} else {
			if (originalData.get(key).isString() != null) {
				field = new TextBoxField();
			} else if (originalData.get(key).isBoolean() != null) {
				field = new CheckBoxField();
			} else if (originalData.get(key).isNumber() != null) {
				field = new NumberBoxField();
			} else {
				throw new Exception("Cannot render field (type not supported): " + key
						+ ". Consider using custom field (FieldConfig.setFieldWidget)");
			}
		}
		field.setFieldConfig(fieldConfig);
		field.setValue(key, originalData.get(key), originalData);
		fields.add(field);
		return field;

	}

	public String getDataJson() {
		JSONObject data = new JSONObject();
		for (String key : fieldOrder){
			try {
				FormField field = getField(key);
				data.put(key, field.getJsonValue());
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		return data.toString();
	}
	
	public String getChangesJson() {
		return null;
	}

	public void setFieldVisible(String key, boolean isVisible) throws Exception {
		getField(key).setVisible(isVisible);
	}
	
	public FormField getField(String key) throws Exception {
		int index = 0;
		for (String field : fieldOrder){
			if (field.equals(key)) {
				return fields.get(index);
			}
			index++;
		}
		throw new Exception("Field not found: " + key);
	}

	public HandlerRegistration addValueChangeHandler(String key, ValueChangeHandler<Void> handler) throws Exception {
		return getField(key).addValueChangeHandler(handler);
	}

}
