package hugzhorolo.client.formlayout;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

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

    private List<String> getStringValuesOrNull() {
      return stringValuesOrNull;
    }

    private Map<String, String> getAnyValueWithDisplayNameOrNull() {
      return anyValueWithDisplayNameOrNull;
    }

  }

  private ListBox listBox = new ListBox();
  private ListBoxConfig config;
  private FormLayoutStyle style = Res.INST.get().style();

  public ListBoxField() {
    listBox.addStyleName(style.ListBoxField());
    listBox.addChangeHandler(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        ValueChangeEvent.fire(ListBoxField.this, null);
      }
    });
  }

  @Override
  public Widget asWidget() {
    return listBox;
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
  public void setValue(JSONValue value, JSONObject formData) {
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
      return new JSONString(listBox.getSelectedValue());
    }
  }

  public void updateValueRange(Map<String, String> anyValueWithDisplayName) {
    listBox.clear();
    config.stringValuesOrNull = null;
    config.anyValueWithDisplayNameOrNull = anyValueWithDisplayName;
    setValueRange();
  }

  public void updateValueRange(List<String> stringValues) {
    listBox.clear();
    config.anyValueWithDisplayNameOrNull = null;
    config.stringValuesOrNull = stringValues;
    setValueRange();
  }

  public void updateValueRange(String... values) {
    listBox.clear();
    config.anyValueWithDisplayNameOrNull = null;
    config.stringValuesOrNull = Arrays.asList(values);
    setValueRange();
  }

}
