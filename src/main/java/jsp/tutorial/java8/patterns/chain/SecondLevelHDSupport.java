package jsp.tutorial.java8.patterns.chain;

/**
 * The second level support (the <b>ConcreteHandler2</b>).
 *
 * @author John Psoroulas
 */
public class SecondLevelHDSupport extends HDSupport {

	public SecondLevelHDSupport() {
	}

	public SecondLevelHDSupport(HDSupport nextSupport) {
		super(nextSupport);
	}

	@Override
	public SupportRequest doHandle(SupportRequest request) {
		return HDSupportHandlerAlgorithms.secondLevelSupport(request);
	}

}
