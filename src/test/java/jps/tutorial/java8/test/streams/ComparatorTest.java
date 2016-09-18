package jps.tutorial.java8.test.streams;

import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.Candidate;

/**
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class ComparatorTest extends TestSupport {

  private static final Logger LOG = LoggerFactory.getLogger(ComparatorTest.class);

  /**
   * Simple sorting test
   */
  public void simpleSort() {
    /* Generate candidates list with random data */
    List<Candidate> candidates = DataUtils.buildCandidates(5);
    /* Sort by the list by name using Comparator interface */
    candidates.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
    /* Print the sorted collection */
    //    DataUtils.printList(candidates);
    /* Check the order */
    Assert.assertTrue(DataUtils.isSorted(
        candidates,
        (a, b) -> a.getName().compareTo(b.getName()) < 0));
  }

  /**
   * Complex sorting test
   */
  public void multiSort() {
    /* Build the candidates list with common name */
    List<Candidate> candidates = DataUtils.buildCandidatesByName("Javaman", 5);
    /* Sort by the list by name and age using Collections API and method references */
    candidates.sort(
        Comparator.comparing(Candidate::getName)
            .thenComparingInt(Candidate::getAge));
    /* Print the sorted collection */
    //    DataUtils.printList(candidates);
    /* Check the order */
    Assert.assertTrue(DataUtils.isSorted(
        candidates,
        (a, b) -> a.getAge() - b.getAge() < 0));
  }

}
