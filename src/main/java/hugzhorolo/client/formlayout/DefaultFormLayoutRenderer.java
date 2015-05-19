package hugzhorolo.client.formlayout;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DefaultFormLayoutRenderer extends VerticalPanel implements FormLayoutRenderer {

  private FormLayoutStyle style = Res.INST.get().style();
  private Map<String, FlowPanel> fieldByName = new HashMap<String, FlowPanel>();

  public DefaultFormLayoutRenderer() {
    addStyleName(style.DefaultFormLayoutRenderer());
    setSpacing(0);

  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void addField(FormField field) {
    Label fieldTitle = new Label(field.getFieldConfig().getFieldName());
    fieldTitle.addStyleName(style.FieldTitle());
    FlowPanel wrapperPanel = new FlowPanel();
    wrapperPanel.add(fieldTitle);
    wrapperPanel.add(field);
    fieldByName.put(field.getFieldConfig().getFieldName(), wrapperPanel);
    add(wrapperPanel);
  }

  @Override
  public void appendWidgetToBottom(IsWidget widget) {
    add(widget);
  }

  @Override
  public void setFieldVisible(FormField field, boolean isVisible) {
    if (fieldByName.containsKey(field.getFieldConfig().getFieldName())) {
      fieldByName.get(field.getFieldConfig().getFieldName()).setVisible(isVisible);
    }
  }

}
