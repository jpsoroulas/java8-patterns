package jsp.tutorial.java8.patterns.chain;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The support levels abstraction (the <b>Handler</b>).
 *
 * @author John Psoroulas
 */
public abstract class HDSupport {

	private static final Logger LOG = LoggerFactory.getLogger(HDSupport.class);

	/**
	 * Holds the next support level.
	 */
	private HDSupport nextSupport;

	public HDSupport() {
	}

	public HDSupport(HDSupport nextSupport) {
		this.nextSupport = nextSupport;
	}

	public SupportRequest handle(SupportRequest request) {
		Validate.notNull(request, "Support request is required!");
		request = doHandle(request);
		if(request.isHandled()) {
			LOG.info("Request type {} handled by {}",
					request.getType(), getClass().getSimpleName());
			return request;
		}else {
			if(null != nextSupport) {
				return nextSupport.handle(request);
			}
			throw new UnsupportedRequestException(request.getType().toString());
		}
	}

	public HDSupport getNextSupport() {
		return nextSupport;
	}

	public void setNextSupport(HDSupport nextSupport) {
		this.nextSupport = nextSupport;
	}

	protected abstract SupportRequest doHandle(SupportRequest request);

}
