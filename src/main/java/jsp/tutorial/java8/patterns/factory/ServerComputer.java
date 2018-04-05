package jsp.tutorial.java8.patterns.factory;

import java.util.Optional;

import jsp.tutorial.java8.patterns.factory.ComputerSpecs.ComputerSpecsBuilder;

/**
 * The computer implementation of the model {@link ComputerModel#SERVER}.
 *
 * @author John Psoroulas
 */
public class ServerComputer extends Computer {

	public ServerComputer() {
		super(ComputerSpecsBuilder.buildExtendedSpec());
	}

	public ServerComputer(ComputerSpecs spec) {
		super(Optional.ofNullable(spec)
				.orElseGet(ComputerSpecsBuilder::buildExtendedSpec));
	}

}
