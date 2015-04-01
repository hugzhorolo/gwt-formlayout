package hugzhorolo.client;

import java.util.logging.Logger;

public class ClientSideLogger {

  private static Logger logger;

  public static Logger get() {
    if (logger == null) {
      logger = Logger.getLogger("ClientSideLogger");
    }
    return logger;
  }
}
