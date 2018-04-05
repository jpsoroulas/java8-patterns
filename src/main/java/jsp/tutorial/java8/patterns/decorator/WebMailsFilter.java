package jsp.tutorial.java8.patterns.decorator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filters the <i>web-mails</i> (the <b>ConcreteDecorator1</b>}).
 *
 * @author John Psoroulas
 */
public class WebMailsFilter extends WebFilterDecorator {

	private static final Logger LOG = LoggerFactory.getLogger(WebMailsFilter.class);

	public WebMailsFilter(WebFilter webFilter) {
		super(webFilter);
	}

	@Override
	public List<String> filter(List<String> urls) {
		LOG.info("Filter web mails");
		return WebFiltersAlgorithms.removeWebMails(super.filter(urls));
	}

}
