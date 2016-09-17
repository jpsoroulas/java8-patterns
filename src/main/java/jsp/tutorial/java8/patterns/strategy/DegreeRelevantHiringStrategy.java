package jsp.tutorial.java8.patterns.strategy;

import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.Degree.DegreeField;

/**
 * This hiring strategy uses the Candidate's degree's field as hiring criteria (the
 * <b>ConcreteStrategy1</b>).
 *
 * @author John Psoroulas
 */
public class DegreeRelevantHiringStrategy implements HiringStrategy {

	private DegreeField degreeField;

	public DegreeRelevantHiringStrategy() {
		this(DegreeField.ENGINEERING);
	}

	public DegreeRelevantHiringStrategy(DegreeField degreeField) {
		this.degreeField = degreeField;
	}

	@Override
	public boolean hire(Candidate candidate) {
		return StrategyAlgorithms.degree(candidate, degreeField);
	}

}
