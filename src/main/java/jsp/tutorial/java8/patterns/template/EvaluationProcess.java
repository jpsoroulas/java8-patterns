package jsp.tutorial.java8.patterns.template;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * The <b>Template class</b> for job candidates evaluation process.
 *
 * @author John Psoroulas
 */
public abstract class EvaluationProcess implements IEvaluationProcess {

	/**
	 * Evaluates the {@link Candidate} for a specific job type.
	 *
	 * @param candidate, the candidate
	 * @return the candidate score
	 */
	@Override
	public int evaluate(Candidate candidate) {
		return commonStep(candidate) + specialStep(candidate);
	}

	/**
	 * The first common evaluation step is expressed with an algorithm that considers the age of the
	 * candidate.
	 *
	 * @param candidate the candidate
	 * @return the score
	 */
	protected int commonStep(Candidate candidate) {
		return ProcessEvaluationAlgorithms.common(candidate);
	}

	/**
	 * The second evaluation step depends on the job type.
	 *
	 * @param candidate
	 * @return the candidate's score
	 */
	protected abstract int specialStep(Candidate candidate);

}
