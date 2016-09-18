package jsp.tutorial.java8.patterns.strategy;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * This hiring strategy uses the Candidate's degree grade as hiring criteria (the
 * <b>ConcreteStrategy2</b>).
 *
 * @author John Psoroulas
 */
public class GradeHiringStrategy implements HiringStrategy {

  private int threashold;

  public GradeHiringStrategy() {
    this(8);
  }

  public GradeHiringStrategy(int threashold) {
    this.threashold = threashold;
  }

  @Override
  public boolean hire(Candidate candidate) {
    return StrategyAlgorithms.grade(candidate, threashold);
  }

}
