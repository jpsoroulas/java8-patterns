package jsp.tutorial.java8.patterns.chain;

/**
 * Raised when all the levels of support cannot handle the {@link SupportRequest request}.
 *
 * @author John Psoroulas
 */
public class UnsupportedRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UnsupportedRequestException() {
  }

  public UnsupportedRequestException(String message) {
    super(message);
  }

}
