package hugzhorolo.client.formlayout;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DefaultFormLayoutRenderer extends VerticalPanel implements FormLayoutRenderer {

  private FormLayoutStyle style = Res.INST.get().style();

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
    add(fieldTitle);
    add(field);
  }

  @Override
  public void appendWidgetToBottom(IsWidget widget) {
    add(widget);
  }

}
