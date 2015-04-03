package hugzhorolo.client.formlayout;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TextBoxField extends FormField {

  public static class TextBoxConfigBuilder {

    private static final String K_MAXLENGTH = "maxLength";
    private JSONObject config = new JSONObject();
    
    public TextBoxConfigBuilder() {
    }

    public TextBoxConfigBuilder maxLength(int maxLength) {
      config.put(K_MAXLENGTH, new JSONNumber(maxLength));
      return this;
    }

    public String build() {
      return config.toString();
    }
    
    public static TextBoxConfigBuilder parse(String json){
      TextBoxConfigBuilder builder = new TextBoxConfigBuilder();
      builder.config = JSONParser.parseLenient(json).isObject();
      return builder;
    }
  }
  
  private TextBoxConfigBuilder specConfig;
  private TextBox textBox = new TextBox();
  private String key;
  private FormLayoutStyle style = Res.INST.get().style();
  
  public TextBoxField() {
    textBox.addStyleName(style.TextBoxField());
  }
  
  @Override
  public void onFieldConfigSet() {
    if (fieldConfig.getFormFieldSpecificConfigJson() != null){
      specConfig = TextBoxConfigBuilder.parse(fieldConfig.getFormFieldSpecificConfigJson());
    }   
    
  }

  @Override
  public Widget asWidget() {
    return textBox;
  }

  @Override
  public String getValue() {
    return new JSONString(textBox.getValue()).toString();
  }
  

  @Override
  public JSONValue getJsonValue() {
    return new JSONString(textBox.getValue());
  }


  @Override
  public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Void> handler) {
    return textBox.addValueChangeHandler(new ValueChangeHandler<String>() {

      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        handler.onValueChange(null);
      }
    });
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    textBox.fireEvent(event);
  }

  @Override
  public void setVisible(boolean isVisible) {
    textBox.setVisible(isVisible);
  }


  @Override
  public void setValue(String valueJson) {
    if (valueJson != null){
      textBox.setText(JSONParser.parseStrict(valueJson).isString().stringValue());
    }
  }

  @Override
  public void setValue(String key, JSONValue value, JSONValue formData) {
    this.key = key;
    if (value != null){
      textBox.setText(value.isString().stringValue());
    }
  }

}
