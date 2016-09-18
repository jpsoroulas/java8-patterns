package jsp.tutorial.java8.patterns.strategy;

import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.Degree.DegreeField;

/**
 * Provides the generic forms of the applied hiring strategies algorithms.
 *
 * @author John Psoroulas
 */
public interface StrategyAlgorithms {

  /**
   * Degree relevant hiring strategy algorithm, the specified candidate is hired if he has a
   * degree relevant to the specified field.
   *
   * @param candidate the candidate
   * @param field the degree field
   * @return true if the candidate is hired, otherwise false.
   */
  static boolean degree(Candidate candidate, DegreeField field) {
    return candidate.getDegrees().stream()
        .anyMatch(d -> field == d.getField());
  }

  /**
   * Grade hiring strategy algorithm, the specified candidate is hired if he has any degree with
   * grade greater than the specified threshold.
   *
   * @param candidate the candidate
   * @param threshold the threshold
   * @return true if the candidate is hired, otherwise false.
   */
  static boolean grade(Candidate candidate, int threshold) {
    return candidate.getDegrees().stream()
        .anyMatch(d -> threshold <= d.getGrade());
  }

}
