package hugzhorolo.client.formlayout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface Res extends ClientBundle {

  public static class INST {

    static Res INST;

    public static Res get() {
      if (INST == null) {
        INST = GWT.create(Res.class);
        INST.style().ensureInjected();
      }
      return INST;
    }
  }

  @Source("FormLayoutStyle.gss")
  public FormLayoutStyle style();

}

