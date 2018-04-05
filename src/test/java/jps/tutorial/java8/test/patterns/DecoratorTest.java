package jps.tutorial.java8.test.patterns;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.decorator.NewsSitesFilter;
import jsp.tutorial.java8.patterns.decorator.SimpleFilter;
import jsp.tutorial.java8.patterns.decorator.WebFilter;
import jsp.tutorial.java8.patterns.decorator.WebFiltersAlgorithms;
import jsp.tutorial.java8.patterns.decorator.WebMailsFilter;

/**
 * Decorator pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class DecoratorTest extends TestSupport {

	private static final Logger LOG = LoggerFactory.getLogger(DecoratorTest.class);

	private final List<String> URLs = Arrays.asList(
			"https://www.facebook.com/",
			"https://twitter.com/",
			"https://mail.google.com",
			"http://www.bbc.com/news",
			"http://www.a.site/");

	/**
	 * Tests the 'old-way' web filters decorators.
	 */
	public void testWebFilters() {
		/* Create a simple filter with the 'old-way' */
		WebFilter filter = new SimpleFilter();
		/* Create a filter decorator with the 'old-way' */
		WebFilter filterDecorator = new NewsSitesFilter(new WebMailsFilter(filter));
		/* Test the results */
		Assert.assertEquals(filter.filter(URLs).size(), 3,
				"Only social media sites should be filtered");
		Assert.assertEquals(filterDecorator.filter(URLs).size(), 1,
				"Social media, web mail and news sites should be filtered");
	}

	/**
	 * Tests the 'lambda-way' web filters decorators.
	 */
	public void testWebFiltersLambda() {
		/* Create a simple filter with the 'lambda-way' */
		Function<List<String>, List<String>> filter = WebFiltersAlgorithms::removeSocialNetworks;
		/* Create a filter decorator with the 'lambda-way' */
		Function<List<String>, List<String>> filterDecorator = Stream.<Function<List<String>, List<String>>> of(
				WebFiltersAlgorithms::removeSocialNetworks,
				WebFiltersAlgorithms::removeWebMails,
				WebFiltersAlgorithms::removeNewsSites)
				.reduce((f, n) -> f.compose(n))
				.get();
		/* Test the results */
		Assert.assertEquals(filter.apply(URLs).size(), 3,
				"Only social media sites should be filtered");
		Assert.assertEquals(filterDecorator.apply(URLs).size(), 1,
				"Social media, web mail and news sites should be filtered");
	}

}
