package jsp.tutorial.java8.patterns.decorator;

import java.util.List;

/**
 * The common API for the Web filters (the <b>Component</b>).
 *
 * @author John Psoroulas
 */
public interface WebFilter {

	/**
	 * Filter the specified URLs
	 *
	 * @param urls the URLs
	 * @return the filtered URLs.
	 */
	public List<String> filter(List<String> urls);

}
