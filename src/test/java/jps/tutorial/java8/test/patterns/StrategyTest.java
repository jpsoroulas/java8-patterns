package jps.tutorial.java8.test.patterns;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.Degree.DegreeField;
import jsp.tutorial.java8.patterns.Employer;
import jsp.tutorial.java8.patterns.strategy.DegreeRelevantHiringStrategy;
import jsp.tutorial.java8.patterns.strategy.GradeHiringStrategy;
import jsp.tutorial.java8.patterns.strategy.HiringStrategy;
import jsp.tutorial.java8.patterns.strategy.StrategyAlgorithms;

/**
 * Strategy pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class StrategyTest extends TestSupport {

	private static final Logger LOG = LoggerFactory.getLogger(StrategyTest.class);

	private Employer employer = new Employer();

	/**
	 * Just to check that employer can hire when the hiring strategy is set.
	 * Check the 'Optional' usage at the {@link Employer#hire(Candidate)} method.
	 */
	public void employerWithoutStrategy() {
		/* The employer is not authorized to hire */
		employer.setHiringStrategy(null);
		/* Build the candidates */
		List<Candidate> candidates = DataUtils.buildCandidates(100);
		// Find the number of hired candidates
		long hired = candidates.stream()
				.filter(c -> employer.hire(c))
				.count();
		/* Test the results */
		Assert.assertEquals(hired, 0, "The employer has no authorization for hiring");
	}

	/**
	 * Test the {@link DegreeRelevantHiringStrategy}
	 */
	public void degreeRelevantHiringStrategy() {
		DegreeField field = DegreeField.ENGINEERING;
		/* ------------ The 'old-way' ------------ */
		/* Create an 'ENGINEERING degree relevant hiring strategy' */
		//		HiringStrategy strategy = new DegreeRelevantHiringStrategy(DegreeField.ENGINEERING);

		/* ------------ The 'lambda-way' ------------*/
		HiringStrategy strategy = candidate -> {
			// Call the generic degree relevant hiring algorithm
			return StrategyAlgorithms.degree(candidate, field);
		};
		// Set the hiring strategy to the employer
		employer.setHiringStrategy(strategy);
		/* Build the candidates */
		List<Candidate> candidates = DataUtils.buildCandidates(100);
		/* Find the expected number of the hired candidates by counting the ones that has ENGINEERING degree field */
		long expectedHired = candidates.stream()
				.filter(c -> c.getDegrees().stream().anyMatch(d -> field == d.getField()))
				.count();
		/* Find the actual number of the hired candidates by counting the ones that hired by applying the degree relevant hiring strategy */
		long actualHired = candidates.stream()
				.filter(c -> employer.hire(c))
				.count();
		/* Test the results */
		Assert.assertEquals(actualHired, expectedHired, "Unexpected number of hired people");
	}

	/**
	 * Test the {@link GradeHiringStrategy}
	 */
	public void gradeHiringStrategy() {
		final int threshold = 8;
		/* ------------ The old way: ------------ */
		/* Create a 'grade' hiring strategy */
		//		HiringStrategy strategy = new GradeHiringStrategy(gradeThreshold);

		/* ------------ The lambda way ------------*/
		HiringStrategy strategy = candidate -> {
			// Call the generic grade hiring algorithm
			return StrategyAlgorithms.grade(candidate, threshold);
		};
		// Set the hiring strategy to the employer
		employer.setHiringStrategy(strategy);
		/* Build the candidates */
		List<Candidate> candidates = DataUtils.buildCandidates(100);
		/* Find the expected number of the hired candidates by counting the ones that has grade greater or equals to the threshold */
		long expectedHired = candidates.stream()
				.filter(c -> c.getDegrees().stream().anyMatch(d -> d.getGrade() >= threshold))
				.count();
		/* Find the actual number of the hired candidates by counting the ones that hired by applying the grade hiring strategy */
		long actualHired = candidates.stream()
				.filter(c -> employer.hire(c))
				.count();
		/* Test the results */
		Assert.assertEquals(actualHired, expectedHired, "Unexpected number of hired people");
	}
}
