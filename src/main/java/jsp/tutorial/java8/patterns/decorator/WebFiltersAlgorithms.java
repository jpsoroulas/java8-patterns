package jsp.tutorial.java8.patterns.decorator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The web filters algorithms.
 *
 * @author John Psoroulas
 */
public interface WebFiltersAlgorithms {

	/**
	 * Removes from the specified URL list, the entries that do not refer to social network sites.
	 *
	 * @param url, the URL list
	 * @return the updated list
	 */
	static List<String> removeSocialNetworks(List<String> urls) {
		return urls.stream()
				.filter(url -> !(url.contains("facebook")
						|| url.contains("twitter")))
				.collect(Collectors.toList());
	}

	/**
	 * Removes from the specified URL list, the entries that do not refer to 'web-mails' sites.
	 *
	 * @param url, the URL list
	 * @return the updated list
	 */
	static List<String> removeWebMails(List<String> urls) {
		return urls.stream()
				.filter(url -> !url.contains("mail"))
				.collect(Collectors.toList());
	}

	/**
	 * Removes from the specified URL list, the entries that do not refer to 'news' sites.
	 *
	 * @param url, the URL list
	 * @return the updated list
	 */
	static List<String> removeNewsSites(List<String> urls) {
		return urls.stream()
				.filter(url -> !url.contains("news"))
				.collect(Collectors.toList());
	}

}
