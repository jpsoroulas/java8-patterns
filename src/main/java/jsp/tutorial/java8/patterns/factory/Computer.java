package jsp.tutorial.java8.patterns.factory;

/**
 * The <b>common interface</b> of the family objects.
 * Holds some base functionality for the computer class hierarchy.
 *
 * @author John Psoroulas
 */
public abstract class Computer {

	public enum ComputerModel {
		HOME, SERVER;
	}

	protected ComputerSpecs specs;

	public Computer(ComputerSpecs spec) {
		this.specs = spec;
	}

	/**
	 * Returns the computer specs.
	 *
	 * @return the specs.
	 */
	public ComputerSpecs getSpecs() {
		return specs;
	}

}
