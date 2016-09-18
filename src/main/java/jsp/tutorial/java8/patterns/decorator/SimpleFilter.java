package jsp.tutorial.java8.patterns.decorator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple web filter. Filters only some of the social media sites (the <b>ConcreteComponent</b>).
 *
 * @author John Psoroulas
 */
public class SimpleFilter implements WebFilter {

  private static final Logger LOG = LoggerFactory.getLogger(SimpleFilter.class);

  @Override
  public List<String> filter(List<String> urls) {
    LOG.info("Filter social media sites");
    return WebFiltersAlgorithms.removeSocialNetworks(urls);
  }

}
