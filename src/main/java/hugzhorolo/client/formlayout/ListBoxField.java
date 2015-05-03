package hugzhorolo.client.formlayout;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: update value range
 * 
 * @author soti
 *
 */
public class ListBoxField extends FormField {

  public static class ListBoxConfig {

    private List<String> stringValuesOrNull;

    private Map<String, String> anyValueWithDisplayNameOrNull;

    public ListBoxConfig stringValues(List<String> stringValues) {
      if (anyValueWithDisplayNameOrNull != null) {
        throw new RuntimeException("Value range already set. Only one of stringValues and "
          + "anyValueWithDisplayname can be used!");
      }
      stringValuesOrNull = stringValues;
      return this;
    }

    public ListBoxConfig stringValues(String... values) {
      return stringValues(Arrays.asList(values));
    }

    public ListBoxConfig anyValueWithDisplayName(Map<String, String> anyValueWithDisplayName) {
      if (stringValuesOrNull != null) {
        throw new RuntimeException("Value range already set. Only one of stringValues and "
          + "anyValueWithDisplayname can be used!");
      }
      this.anyValueWithDisplayNameOrNull = anyValueWithDisplayName;
      return this;
    }

    public List<String> getStringValuesOrNull() {
      return stringValuesOrNull;
    }

    public Map<String, String> getAnyValueWithDisplayNameOrNull() {
      return anyValueWithDisplayNameOrNull;
    }

  }

  private ListBox listBox = new ListBox();
  private ListBoxConfig config;

  public ListBoxField() {
  }

  @Override
  public Widget asWidget() {
    return listBox;
  }

  @Override
  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Void> handler) {
    return null;
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
  }

  @Override
  public void onFieldConfigSet() {
    if (fieldConfig.getFormFieldSpecificConfig() != null) {
      config = (ListBoxConfig) fieldConfig.getFormFieldSpecificConfig();
    } else {
      throw new RuntimeException(
        "ListBox isn't configured. Use FieldConfig.setSpecificConfig and ListBoxConfig to provide value range!");
    }
    setValueRange();
  }

  private void setValueRange() {
    if (config.getStringValuesOrNull() != null) {
      for (String value : config.getStringValuesOrNull()) {
        listBox.addItem(value);
      }
    } else {
      for (Entry<String, String> value : config.getAnyValueWithDisplayNameOrNull().entrySet()) {
        listBox.addItem(value.getValue(), value.getKey());
      }
    }
  }

  @Override
  public void setValue(JSONValue value, JSONValue formData) {
    setValue(value);
  }

  private void setValue(JSONValue value) {
    if (config.stringValuesOrNull != null) {
      String stringValue = value.isString().stringValue();
      listBox.setSelectedIndex(config.stringValuesOrNull.indexOf(stringValue));
    } else {
      for (int i = 0; i < listBox.getItemCount(); i++) {
        if (listBox.getValue(i).equals(value.toString())) {
          listBox.setSelectedIndex(i);
          break;
        }
      }
    }
  }

  @Override
  public void setValue(String valueJson) {
    setValue(JSONParser.parseStrict(valueJson));
  }

  @Override
  public JSONValue getJsonValue() {
    if (listBox.getSelectedIndex() == -1) return JSONNull.getInstance();
    if (config.stringValuesOrNull != null) {
      return new JSONString(listBox.getSelectedValue());
    } else {
      return JSONParser.parseStrict(listBox.getSelectedValue());
    }
  }

  @Override
  public String getValue() {
    return getJsonValue().toString();
  }

  @Override
  public void setVisible(boolean isVisible) {
    listBox.setVisible(isVisible);
  }

}
