package jsp.tutorial.java8.patterns.strategy;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * The hiring strategy API (the <b>Strategy</b>).
 *
 * @author John Psoroulas
 */
public interface HiringStrategy {

  /**
   * Decides whether a {@link Candidate} should be hired or not.
   *
   * @param candidate the candidate
   * @return true if the candidate is hired, otherwise false.
   */
  boolean hire(Candidate candidate);

}
