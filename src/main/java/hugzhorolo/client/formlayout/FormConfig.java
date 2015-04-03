package hugzhorolo.client.formlayout;

import java.util.ArrayList;
import java.util.List;

public class FormConfig {

  private FormLayoutRenderer renderer = new DefaultFormLayoutRenderer();
  
  private List<FieldConfig> fieldConfigs = new ArrayList<FieldConfig>();

  public FormConfig setRenderer(FormLayoutRenderer renderer){
    this.renderer = renderer;
    return this;
  }
  
  public FormConfig addFieldConfig(FieldConfig fieldConfig) {
    fieldConfigs.add(fieldConfig);
    return this;
  }

  public FieldConfig getFieldConfigs(String key) {
    for (FieldConfig config : fieldConfigs) {
      if (config.getFieldName().equals(key)) {
        return config;
      }
    }
    return null;
  }

  public FormLayoutRenderer getRenderer() {
    return renderer;
  }

}
