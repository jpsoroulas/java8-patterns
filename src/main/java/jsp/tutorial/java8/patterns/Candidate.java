package jsp.tutorial.java8.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.Validate;

/**
 * Represents a job-position candidate.
 *
 * @author John Psoroulas
 */
public class Candidate {

	public enum Gender {
		MALE, FEMALE;
	}

	private String name;

	private int age;

	private Gender gender;

	private List<Degree> degrees;

	// The candidate may not give a contact info
	private Optional<ContactInfo> contactInfo;

	/* Optionally, put here more info about the candidate.
	 * Not all the info is mandatory. You can selectively set desire info using
	 * the Builder pattern. */

	// ..., more candidate info

	private Candidate(CandidateBuilder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.gender = builder.gender;
		this.degrees = builder.degrees;
		this.contactInfo = builder.contactInfo;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void addDegree(Degree degree) {
		this.degrees.add(degree);
	}

	public Optional<ContactInfo> getContactInfo() {
		return contactInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder
				.append("Candidate [name=")
				.append(name)
				.append(", age=")
				.append(age)
				.append(", gender=")
				.append(gender)
				.append(", degrees=")
				.append(degrees)
				.append(", contactInfo=")
				.append(contactInfo)
				.append("]");
		return builder.toString();
	}

	public static class CandidateBuilder {

		private String name;

		private int age;

		private Gender gender;

		private List<Degree> degrees = new ArrayList<>();

		private Optional<ContactInfo> contactInfo;

		public CandidateBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public CandidateBuilder withAge(Integer age) {
			this.age = age;
			return this;
		}

		public CandidateBuilder withGender(Gender gender) {
			this.gender = gender;
			return this;
		}

		public CandidateBuilder withDegree(Degree degree) {
			this.degrees.add(degree);
			return this;
		}

		public CandidateBuilder withDegrees(List<Degree> degrees) {
			this.degrees.addAll(degrees);
			return this;
		}

		public CandidateBuilder withContactInfo(ContactInfo contactInfo) {
			this.contactInfo = Optional.ofNullable(contactInfo);
			return this;
		}

		public Candidate build() {
			// TODO some validation about the age
			Validate.notEmpty(name, "Undefined candidate name!");
			Validate.notNull(gender, "Undefined candidate gender!");
			return new Candidate(this);
		}

	}

}
