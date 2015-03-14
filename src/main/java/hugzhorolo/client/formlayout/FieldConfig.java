package hugzhorolo.client.formlayout;


public class FieldConfig {

	private String fieldName;
	
	private FormField fieldWidget;
	
	private String formFieldSpecificConfigJson;

	public FieldConfig(String fieldName) {
		super();
		this.fieldName = fieldName;
	}
	
	public FieldConfig setFieldWidget(FormField formField){
		return this;
	}
	
	public FieldConfig setSpecificConfig(String specificConfigJson){
		return this;
	}

	public String getFieldName() {
		return fieldName;
	}

	public FormField getFieldWidget() {
		return fieldWidget;
	}

	public String getFormFieldSpecificConfigJson() {
		return formFieldSpecificConfigJson;
	}
	
	
	
}
