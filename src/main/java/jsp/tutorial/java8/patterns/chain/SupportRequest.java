package jsp.tutorial.java8.patterns.chain;

/**
 * The support client request.
 *
 * @author John Psoroulas
 */
public class SupportRequest {

  /**
   * Some request types (used by the various support levels to decide
   * whether can handle a request or not).
   */
  public enum RequestType {
    LEVEL1, LEVEL2, LEVEL3, LEVEL4
  }

  private RequestType type;

  private boolean handled = false;

  public SupportRequest() {
    this.type = RequestType.LEVEL1;
  }

  public SupportRequest(RequestType type) {
    this.type = type;
  }

  public boolean isHandled() {
    return handled;
  }

  public void setHandled(boolean handled) {
    this.handled = handled;
  }

  public RequestType getType() {
    return type;
  }

  public void setType(RequestType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "SupportRequest [type=" + type + ", handled=" + handled + "]";
  }

}
