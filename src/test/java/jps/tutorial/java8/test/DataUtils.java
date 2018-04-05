package jps.tutorial.java8.test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.Candidate.Gender;
import jsp.tutorial.java8.patterns.ContactInfo;
import jsp.tutorial.java8.patterns.Degree;
import jsp.tutorial.java8.patterns.Degree.DegreeField;
import jsp.tutorial.java8.patterns.JobInfo;
import jsp.tutorial.java8.patterns.JobInfo.JobCategory;
import one.util.streamex.StreamEx;

/**
 * Data generation util
 * @author John Psoroulas
 */
public final class DataUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DataUtils.class);

	// some Suppliers
	public static final Supplier<String> RAND_STRING_SUPPL =
			() -> RandomStringUtils.randomAlphabetic(15);

	public static final IntSupplier RAND_AGE_SUPPL =
			() -> RandomUtils.nextInt(20, 50);

	public static final Supplier<Gender> RAND_GENDER_SUPPL =
			() -> Gender.values()[RandomUtils.nextInt(0, Gender.values().length)];

	public static final IntSupplier RAND_GRADE_SUPPL = () -> RandomUtils.nextInt(0, 10);

	public static final Supplier<DegreeField> RAND_DEGREE_FIELD_SUPPL =
			() -> DegreeField.values()[RandomUtils.nextInt(0, DegreeField.values().length)];

	public static final Supplier<JobCategory> RAND_JOB_CAT_SUPPL =
			() -> JobCategory.values()[RandomUtils.nextInt(0, JobCategory.values().length)];

	private DataUtils() {
	}

	public static Candidate buildCandidate(Supplier<String> strSuppl, IntSupplier ageSuppl,
			Supplier<Gender> genderSuppl) {
		return new Candidate.CandidateBuilder()
				.withName(strSuppl.get())
				.withAge(ageSuppl.getAsInt())
				.withGender(genderSuppl.get())
				.withDegree(buildDegree())
				.withContactInfo(new ContactInfo(strSuppl.get(), genRandomBoolean()))
				.build();
	}

	public static Candidate buildCandidate() {
		return buildCandidate(
				RAND_STRING_SUPPL,
				RAND_AGE_SUPPL,
				RAND_GENDER_SUPPL);
	}

	public static List<Candidate> buildCandidates(
			Supplier<String> strSuppl,
			IntSupplier ageSuppl,
			Supplier<Gender> genderSuppl,
			int numberOfCandidates) {
		return IntStream
				.range(0, numberOfCandidates) /* Generate a stream of int numbers */
				.mapToObj( /* Map numbers to 'Candidate' objects */
						i -> buildCandidate(strSuppl, ageSuppl, genderSuppl))
				.collect(Collectors.toList()); /* collect 'Candidate' objects to list */
	}

	public static List<Candidate> buildCandidates(int numberOfCandidates) {
		return buildCandidates(
				RAND_STRING_SUPPL,
				RAND_AGE_SUPPL,
				RAND_GENDER_SUPPL,
				numberOfCandidates);
	}

	public static List<Candidate> buildCandidatesByName(String name, int ns) {
		return buildCandidates(
				() -> name,
				RAND_AGE_SUPPL,
				RAND_GENDER_SUPPL,
				ns);
	}

	public static Degree buildDegree() {
		return new Degree.DegreeBuilder()
				.withField(RAND_DEGREE_FIELD_SUPPL.get())
				.withHolderName(RAND_STRING_SUPPL.get())
				.withGrade(RAND_GRADE_SUPPL.getAsInt())
				.build();
	}

	public static Degree buildDegreeByField(DegreeField field) {
		return new Degree.DegreeBuilder()
				.withField(field)
				.withHolderName(RAND_STRING_SUPPL.get())
				.withGrade(RAND_GRADE_SUPPL.getAsInt())
				.build();
	}

	public static List<Degree> buildDegreesByField(DegreeField field, int numberOfDegrees) {
		return IntStream
				.range(0, numberOfDegrees)
				.mapToObj(i -> buildDegreeByField(field))
				.collect(Collectors.toList());
	}

	public static List<JobInfo> buildJobs(int numberOfJobs) {
		return IntStream
				.range(0, numberOfJobs)
				.mapToObj(i -> new JobInfo(RAND_JOB_CAT_SUPPL.get(), RAND_STRING_SUPPL.get()))
				.collect(Collectors.toList());
	}

	public static <T> boolean isSorted(List<T> source, BiFunction<T, T, Boolean> unsc) {
		return StreamEx.of(source).pairMap(unsc).has(true);
	}

	public static void printList(List<?> list) {
		list.forEach((e) -> LOG.info(e.toString()));
	}

	public static boolean genRandomBoolean() {
		return Math.random() < 0.5;
	}

	//	public static <T> boolean isSorted(List<T> source, BiFunction<T, T, Boolean> unsc) {
	//				IntStream.range(1, arrayList.size())
	//		    .mapToObj(i -> new Pair(arrayList.get(i-1), arrayList.get(i)))
	//		    .forEach(System.out::println);
	//	}

}
