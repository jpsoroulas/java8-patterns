package jsp.tutorial.java8.patterns;

import java.util.Optional;

import jsp.tutorial.java8.patterns.strategy.HiringStrategy;

/**
 * The employer that decides who candidate is hired based on the applied {@link HiringStrategy}.
 *
 * @author John Psoroulas
 */
public class Employer {

	/* Lets assume that the Employer will have the authorization to hire people,
	 * if the a hiringStrategy is set. This can be expressed with the Optional in Java 8 */
	private Optional<HiringStrategy> hiringStrategy = Optional.empty();

	public Employer() {
	}

	public Employer(HiringStrategy hiringStrategy) {
		this.hiringStrategy = Optional.of(hiringStrategy);
	}

	public void setHiringStrategy(HiringStrategy hiringStrategy) {
		this.hiringStrategy = Optional.ofNullable(hiringStrategy);
	}

	public boolean hire(Candidate candidate) {
		/* If the hiringStrategy is set the 'mapper' at the map is called which determines the
		 * result of the method call. Otherwise, return false. The 'orElseThrow can be used' according
		 * the requirements */
		return hiringStrategy.map(s -> s.hire(candidate)).orElse(Boolean.FALSE);
	}
}
