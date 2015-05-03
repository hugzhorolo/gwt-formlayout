package hugzhorolo.client.formlayout;


public class FieldConfig {

  private String fieldName;

  private FormField fieldWidget;

  private Object formFieldSpecificConfig;

  public FieldConfig(String fieldName) {
    super();
    this.fieldName = fieldName;
  }

  public FieldConfig setFieldWidget(FormField formField) {
    this.fieldWidget = formField;
    return this;
  }

  public FieldConfig setSpecificConfig(Object specificConfig) {
    this.formFieldSpecificConfig = specificConfig;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public FormField getFieldWidget() {
    return fieldWidget;
  }

  public Object getFormFieldSpecificConfig() {
    return formFieldSpecificConfig;
  }



}
