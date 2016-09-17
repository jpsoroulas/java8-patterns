package jsp.tutorial.java8.patterns.decorator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filters the <i>news</i> web sites (the <b>ConcreteDecorator2</b>}).
 *
 * @author John Psoroulas
 */
public class NewsSitesFilter extends WebFilterDecorator {

	private static final Logger LOG = LoggerFactory.getLogger(NewsSitesFilter.class);

	public NewsSitesFilter(WebFilter webFilter) {
		super(webFilter);
	}

	@Override
	public List<String> filter(List<String> urls) {
		LOG.info("Filter news web sites");
		return WebFiltersAlgorithms.removeNewsSites(super.filter(urls));
	}

}
