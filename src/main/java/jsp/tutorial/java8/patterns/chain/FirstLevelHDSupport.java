package jsp.tutorial.java8.patterns.chain;

/**
 * The first level support (<b>the ConcreteHandler1</b>).
 *
 * @author John Psoroulas
 */
public class FirstLevelHDSupport extends HDSupport {

	public FirstLevelHDSupport() {
	}

	public FirstLevelHDSupport(HDSupport nextSupport) {
		super(nextSupport);
	}

	@Override
	public SupportRequest doHandle(SupportRequest request) {
		return HDSupportHandlerAlgorithms.firstLevelSupport(request);
	}

}
