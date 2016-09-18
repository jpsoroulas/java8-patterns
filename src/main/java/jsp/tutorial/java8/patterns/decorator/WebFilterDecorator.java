package jsp.tutorial.java8.patterns.decorator;

import java.util.List;

/**
 * Wraps the {@link WebFilter} component to enable additional filtering (e.g. news
 * websites and web mails) (the <b>Decorator</b>).
 *
 * @author John Psoroulas
 */
public class WebFilterDecorator implements WebFilter {

  /**
   * The wrapped filter.
   */
  protected WebFilter webFilter;

  public WebFilterDecorator(WebFilter webFilter) {
    this.webFilter = webFilter;
  }

  @Override
  public List<String> filter(List<String> urls) {
    return webFilter.filter(urls);
  }

}
