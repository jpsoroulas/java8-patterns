package jsp.tutorial.java8.patterns.template;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * The evaluation process for Engineer job type (the <b><ConcreteTemplate1</b>).
 *
 * @author John Psoroulas
 */
public class EngineerEvaluationProcess extends EvaluationProcess {

	@Override
	protected int specialStep(Candidate candidate) {
		return ProcessEvaluationAlgorithms.engineer(candidate);
	}
}
