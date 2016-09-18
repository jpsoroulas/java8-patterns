package jsp.tutorial.java8.patterns.chain;

/**
 * The third level support (the <b>ConcreteHandler3</b>).
 *
 * @author John Psoroulas
 */
public class ThirdLevelHDSupport extends HDSupport {

  public ThirdLevelHDSupport() {
  }

  public ThirdLevelHDSupport(HDSupport nextSupport) {
    super(nextSupport);
  }

  @Override
  public SupportRequest doHandle(SupportRequest request) {
    return HDSupportHandlerAlgorithms.thirdLevelSupport(request);
  }

}
