package jsp.tutorial.java8.patterns.factory;

import java.util.EnumMap;
import java.util.function.Supplier;

import jsp.tutorial.java8.patterns.factory.Computer.ComputerModel;

/**
 * The <b>factory</b> for creating {@link Computer}.
 *
 * @author John Psoroulas
 */
public final class ComputerFactory {

	private ComputerFactory() {
	}

	/**
	 * Creates a {@link Computer} of the specified model using the 'old-way'.
	 *
	 * @param model the model
	 * @return the computer
	 */
	public static Computer create(ComputerModel model) {
		Computer computer = null;
		switch(model) {
			case HOME:
				computer = new HomeComputer();
				break;
			case SERVER:
				computer = new ServerComputer();
				break;
			default:
				throw new IllegalStateException("Model " + model + " is not available yet!");
		}
		return computer;
	}

	/**
	 * Holds the computer constructors.
	 */
	private static final EnumMap<ComputerModel, Supplier<Computer>> customComputers =
			new EnumMap<>(ComputerModel.class);
	static {
		customComputers.put(ComputerModel.HOME, HomeComputer::new);
		customComputers.put(ComputerModel.SERVER, ServerComputer::new);
	}

	/**
	 * Creates a {@link Computer} of the specified model using the 'lambda-way'.
	 *
	 * @param model the model
	 * @return the computer
	 */
	public static Computer createLambda(ComputerModel model) {
		return customComputers.get(model).get();
	}

}
