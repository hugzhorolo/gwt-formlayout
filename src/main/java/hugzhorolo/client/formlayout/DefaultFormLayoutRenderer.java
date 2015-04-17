package hugzhorolo.client.formlayout;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DefaultFormLayoutRenderer extends VerticalPanel implements FormLayoutRenderer {

  private FormLayoutStyle style = Res.INST.get().style();
  
  public DefaultFormLayoutRenderer() {
    addStyleName(style.DefaultFormLayoutRenderer());
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void addField(FormField field) {
    add(new Label(field.getFieldConfig().getFieldName()));
    add(field);
  }
  
}
