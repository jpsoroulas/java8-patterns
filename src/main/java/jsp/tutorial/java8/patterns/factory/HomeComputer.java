package jsp.tutorial.java8.patterns.factory;

import jsp.tutorial.java8.patterns.factory.ComputerSpecs.ComputerSpecsBuilder;

/**
 * The computer implementation of the model {@link ComputerModel#HOME}.
 *
 * @author John Psoroulas
 */
public class HomeComputer extends Computer {

  public HomeComputer() {
    super(ComputerSpecsBuilder.buildStandardSpec());
  }

  public HomeComputer(ComputerSpecs spec) {
    super(spec != null ? spec : ComputerSpecsBuilder.buildStandardSpec());
  }

}
