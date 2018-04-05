package jsp.tutorial.java8.patterns.template;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * It is not included at the template method pattern. Added to have a common base API for
 * {@link EvaluationProcess} and {@link EvaluationProcessLambda} for programming purposes.
 *
 * @author John Psoroulas
 */
public interface IEvaluationProcess {

	int evaluate(Candidate candidate);

}
