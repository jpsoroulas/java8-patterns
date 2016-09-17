package jps.tutorial.java8.test.patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import jsp.tutorial.java8.patterns.template.EngineerEvaluationProcess;
import jsp.tutorial.java8.patterns.template.EvaluationProcessLambda;
import jsp.tutorial.java8.patterns.template.ManagerEvaluationProcess;
import jsp.tutorial.java8.patterns.template.ProcessEvaluationAlgorithms;

/**
 * Template pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class TemplateTest extends BaseTemplatePatternTest {

	private static final Logger LOG = LoggerFactory.getLogger(TemplateTest.class);

	/**
	 * Tests the 'old-way' {@link EngineerEvaluationProcess}.
	 */
	public void engineerEvaluationProcess() {
		/* Create the appropriate subclass of the template class */
		evaluationProcess(new EngineerEvaluationProcess(), ProcessEvaluationAlgorithms::engineer);
	}

	/**
	 * Tests the 'old-way' {@link ManagerEvaluationProcess}.
	 */
	public void managerEvaluationProcess() {
		/* Create the appropriate subclass of the template class */
		evaluationProcess(new ManagerEvaluationProcess(), ProcessEvaluationAlgorithms::management);
	}

	/**
	 * Tests the 'lambda way' {@link EngineerEvaluationProcess}.
	 */
	public void engineerEvaluationProcessLambda() {
		/* No subclass, move from polymorphism to composition by passing the appropriate lambda at the constructor */
		evaluationProcessLambda(new EvaluationProcessLambda(ProcessEvaluationAlgorithms::engineer));
	}

	/**
	 * Tests the 'lambda way' {@link ManagerEvaluationProcess}.
	 */
	public void managerEvaluationProcessLambda() {
		/* No subclass, move from polymorphism to composition by passing the appropriate lambda at the constructor */
		evaluationProcessLambda(new EvaluationProcessLambda(ProcessEvaluationAlgorithms::management));
	}

}
