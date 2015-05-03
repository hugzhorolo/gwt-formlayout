package hugzhorolo.client.formlayout;

import com.google.gwt.user.client.ui.IsWidget;

public interface FormLayoutRenderer extends IsWidget {

  public void addField(FormField field);

  public void appendWidgetToBottom(IsWidget widget);
}
