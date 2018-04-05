package jsp.tutorial.java8.patterns.chain;

import jsp.tutorial.java8.patterns.chain.SupportRequest.RequestType;

/**
 * Provides the support levels algorithms for handling the {@link SupportRequest}.
 *
 * @author John Psoroulas
 */
public interface HDSupportHandlerAlgorithms {

	/**
	 * The first level support algorithm implementation for a support request.
	 *
	 * @param request the support request.
	 * @return the support request
	 */
	static SupportRequest firstLevelSupport(SupportRequest request) {
		/* Can handle only the request LEVEL1 */
		if(RequestType.LEVEL1 == request.getType()) {
			request.setHandled(true);
		}
		return request;
	}

	/**
	 * The second level support algorithm implementation for a support request.
	 *
	 * @param request the support request.
	 * @return the support request
	 */
	static SupportRequest secondLevelSupport(SupportRequest request) {
		/* Can handle only the request LEVEL2 */
		if(RequestType.LEVEL2 == request.getType()) {
			request.setHandled(true);
		}
		return request;
	}

	/**
	 * The third level support algorithm for a support request.
	 *
	 * @param request the support request.
	 * @return the support request
	 */
	static SupportRequest thirdLevelSupport(SupportRequest request) {
		/* Can handle only the request LEVEL3 */
		if(RequestType.LEVEL3 == request.getType()) {
			request.setHandled(true);
		}
		return request;
	}

}
