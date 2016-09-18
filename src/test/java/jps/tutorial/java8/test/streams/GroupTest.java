package jps.tutorial.java8.test.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.Degree;
import jsp.tutorial.java8.patterns.Degree.DegreeField;

/**
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class GroupTest extends TestSupport {

  private static final Logger LOG = LoggerFactory.getLogger(GroupTest.class);

  /**
   * Simple group test
   * List<T> --> group by collector
   * List<Degree> --> group by candidate name
   */
  public void simpleGroup() {
    /* Build Degrees for various field and add them into a single list */
    List<Degree> degrees = DataUtils.buildDegreesByField(DegreeField.ENGINEERING, 10);
    degrees.addAll(DataUtils.buildDegreesByField(DegreeField.CHEMISTRY, 15));
    degrees.addAll(DataUtils.buildDegreesByField(DegreeField.PHYSICS, 20));
    /* Group Degrees by field */
    Map<DegreeField, List<Degree>> grouped = degrees.stream() /* Convert list to stream */
        .collect(Collectors.groupingBy(Degree::getField)); /* Group by Degree field */
    /* Check the groups size */
    Assert.assertEquals(grouped.get(DegreeField.ENGINEERING).size(), 10);
    Assert.assertEquals(grouped.get(DegreeField.CHEMISTRY).size(), 15);
    Assert.assertEquals(grouped.get(DegreeField.PHYSICS).size(), 20);
  }

  /**
   * Transform and group test
   * T --> transform to type R[] --> flatten arrays --> group by collector
   * int --> transform to type Degree[] --> flatten arrays --> group by degree field
   */
  public void transformAndGroup() {
    Map<DegreeField, List<Degree>> res = IntStream.range(0, 5) /* Create an int stream */
        .mapToObj( /* For each int produce an array with two degrees */
            i -> {
              return new Degree[] {
                  DataUtils.buildDegreeByField(DegreeField.ENGINEERING),
                  DataUtils.buildDegreeByField(DegreeField.CHEMISTRY)
              };
            })
        .flatMap(a -> Arrays.stream(a)) /* Flatten the array */
        .collect(Collectors.groupingBy(Degree::getField)); /* Group by degree field */
    //        .forEach((k, v) -> LOG.info("key {}, value {}", k, v));
    /* Check the groups size */
    Assert.assertEquals(res.get(DegreeField.ENGINEERING).size(), 5);
    Assert.assertEquals(res.get(DegreeField.CHEMISTRY).size(), 5);
  }
}
