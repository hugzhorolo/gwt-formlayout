package hugzhorolo.client.formlayout;


public class FieldConfig {

  private String fieldName;

  private String fieldDisplayName;

  private FormField fieldWidget;

  private Object formFieldSpecificConfig;

  public FieldConfig(String fieldName) {
    super();
    this.fieldName = fieldName;
  }

  public FieldConfig setFieldDisplayName(String fieldDisplayName) {
    this.fieldDisplayName = fieldDisplayName;
    return this;
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

  public String getFieldDisplayName() {
    if (fieldDisplayName == null)
      return fieldName;
    else
      return fieldDisplayName;
  }

  public FormField getFieldWidget() {
    return fieldWidget;
  }

  public Object getFormFieldSpecificConfig() {
    return formFieldSpecificConfig;
  }



}
