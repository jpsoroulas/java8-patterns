package jsp.tutorial.java8.patterns.template;

import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.Degree.DegreeField;

/**
 * The applied process evaluation algorithms with the form of lambdas
 * To comply with the DRY principle, use them at both 'old' and 'lambda' way implementations.
 *
 * @author John Psoroulas
 */
public interface ProcessEvaluationAlgorithms {
	/**
	 * Give some extra points if the specified candidate is less than 50 years old.
	 *
	 * @param c the candidate
	 * @return the score
	 */
	static int common(Candidate c) {
		return c.getAge() > 50 ? 10 : 20;
	}

	/**
	 * Give 10 extra points for each candidate's degree relevant with engineering and physical
	 * sciences.
	 *
	 * @param c the candidate
	 * @return the score
	 */
	static int engineer(Candidate c) {
		return c.getDegrees().stream()
				.mapToInt(d -> {
					return DegreeField.MANAGEMENT == d.getField() ? 10 : 20;
				})
				.sum();
	}

	/**
	 * Give 10 points if the specified candidate has a degree relevant with Management otherwise no
	 * points are given. Only one Management degree counts to the score.
	 *
	 * @param c the candidate
	 * @return the score
	 */
	static int management(Candidate c) {
		return c.getDegrees().stream()
				.filter(d -> DegreeField.MANAGEMENT == d.getField())
				.findAny()
				.map(d -> 10)
				.orElse(0);
	}
}
