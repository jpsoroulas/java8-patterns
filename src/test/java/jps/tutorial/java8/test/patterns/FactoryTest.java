package jps.tutorial.java8.test.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.factory.Computer;
import jsp.tutorial.java8.patterns.factory.Computer.ComputerModel;
import jsp.tutorial.java8.patterns.factory.ComputerFactory;
import jsp.tutorial.java8.patterns.factory.ComputerSpecs;
import jsp.tutorial.java8.patterns.factory.ComputerSpecs.ComputerSpecsBuilder;
import jsp.tutorial.java8.patterns.factory.HomeComputer;
import jsp.tutorial.java8.patterns.factory.ServerComputer;

/**
 * Factory pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class FactoryTest extends TestSupport {

  private static final Logger LOG = LoggerFactory.getLogger(FactoryTest.class);

  /**
   * Tests the {@link HomeComputer} 'old-way' creation.
   */
  public void createHomeComputer() {
    /* Create a home computer with the built-in specs */
    Computer computer = ComputerFactory.create(ComputerModel.HOME);
    ComputerSpecs spec = computer.getSpecs();
    ComputerSpecs sdspec = ComputerSpecsBuilder.buildStandardSpec();
    /* Test the results */
    Assert.assertEquals(spec.getCores(), sdspec.getCores(),
        "Unexpected number of cores");
    Assert.assertEquals(spec.getDisks(), sdspec.getDisks(),
        "Unexpected number of disks");
    Assert.assertEquals(spec.getMemory(), sdspec.getMemory(),
        "Unexpected memory size");
  }

  /**
   * Tests the {@link ServerComputer} 'lambda-way' creation.
   */
  public void createServerComputerLambda() {
    /* Create a server computer with the built-in specs */
    Computer computer = ComputerFactory.createLambda(ComputerModel.SERVER);
    ComputerSpecs spec = computer.getSpecs();
    ComputerSpecs exspec = ComputerSpecsBuilder.buildExtendedSpec();
    /* Test the results */
    Assert.assertEquals(spec.getCores(), exspec.getCores(),
        "Unexpected number of cores");
    Assert.assertEquals(spec.getDisks(), exspec.getDisks(),
        "Unexpected number of disks");
    Assert.assertEquals(spec.getMemory(), exspec.getMemory(),
        "Unexpected memory size");
  }

}
