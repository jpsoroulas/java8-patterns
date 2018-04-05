package jsp.tutorial.java8.patterns.template;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * The evaluation process for Manager job type (the <b><<ConcreteTemplate2</b>).
 *
 * @author John Psoroulas
 */
public class ManagerEvaluationProcess extends EvaluationProcess {

	@Override
	protected int specialStep(Candidate candidate) {
		return ProcessEvaluationAlgorithms.management(candidate);
	}
}
