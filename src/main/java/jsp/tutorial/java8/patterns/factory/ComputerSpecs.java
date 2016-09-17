package jsp.tutorial.java8.patterns.factory;

import jsp.tutorial.java8.patterns.factory.Computer.ComputerModel;

/**
 * Specifications for the available {@link ComputerModel computer models}.
 * Specifications are Immutable, once set, cannot be modified.
 *
 * @author John Psoroulas
 */
public class ComputerSpecs {

	/**
	 * The total number of cores.
	 */
	private int cores;

	/**
	 * The total number of disk drives.
	 */
	private int disks;

	/**
	 * The RAM in GB
	 */
	private long memory;

	/* Optionally, put here more properties to define some extra computer's features.
	 * Not all the features are mandatory. You can selectively set the desire features
	 * using the Builder pattern. */

	// ..., some extra features

	private ComputerSpecs(ComputerSpecsBuilder builder) {
		this.cores = builder.cores;
		this.disks = builder.disks;
		this.memory = builder.memory;
	}

	public int getCores() {
		return cores;
	}

	public int getDisks() {
		return disks;
	}

	public long getMemory() {
		return memory;
	}

	@Override
	public String toString() {
		return "ComputerSpec [cores=" + cores + ", disks=" + disks + ", memory=" + memory + "]";
	}

	/**
	 * Builds {@link ComputerSpecs}.
	 *
	 * @author John Psoroulas
	 */
	public static class ComputerSpecsBuilder {

		private int cores = 1;

		private int disks = 1;

		private long memory = 2;

		public ComputerSpecsBuilder withCores(int cores) {
			this.cores = cores;
			return this;
		}

		public ComputerSpecsBuilder withDisks(int disks) {
			this.disks = disks;
			return this;
		}

		public ComputerSpecsBuilder withMemory(long memory) {
			this.memory = memory;
			return this;
		}

		public ComputerSpecs build() {
			// TODO some validation
			return new ComputerSpecs(this);
		}

		public ComputerSpecsBuilder initFromSpec(ComputerSpecs specs) {
			this.cores = specs.getCores();
			this.disks = specs.getDisks();
			this.memory = specs.getMemory();
			return this;
		}

		/**
		 * Builds the standard computer specification.
		 *
		 * @return the standard specification.
		 */
		public static ComputerSpecs buildStandardSpec() {
			return new ComputerSpecsBuilder()
					.withCores(4)
					.withDisks(2)
					.withMemory(8)
					.build();
		}

		/**
		 * Builds the extended computer specification.
		 *
		 * @return the extended specification.
		 */
		public static ComputerSpecs buildExtendedSpec() {
			return new ComputerSpecsBuilder()
					.withCores(8)
					.withDisks(4)
					.withMemory(16)
					.build();
		}

	}

}
