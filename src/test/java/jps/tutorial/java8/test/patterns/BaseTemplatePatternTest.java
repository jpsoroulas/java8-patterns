package jps.tutorial.java8.test.patterns;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.template.EvaluationProcess;
import jsp.tutorial.java8.patterns.template.EvaluationProcessLambda;
import jsp.tutorial.java8.patterns.template.IEvaluationProcess;
import jsp.tutorial.java8.patterns.template.ProcessEvaluationAlgorithms;

/**
 * Basic functionality for Template pattern tests.
 *
 * @author John Psoroulas
 */
public class BaseTemplatePatternTest extends TestSupport {

	protected void evaluationProcessLambda(EvaluationProcessLambda process) {
		evaluationProcess(process, process.getSpecialEvaluation());
	}

	/**
	 * Performs the evaluation process test for the specific {@link EvaluationProcess} type and the
	 * respective special evaluation function
	 * @param process the evaluation process
	 * @param specialEvaluation the special evaluation function depended on the job type
	 */
	/* PLEASE NOTE that the used algorithm is not the optimal and it may look naive on purpose,
	 * since this code also targets to show java 8 lamda and streams features */
	protected void evaluationProcess(IEvaluationProcess process, ToIntFunction<Candidate> specialEvaluation) {
		/* Build the candidates */
		List<Candidate> candidates = DataUtils.buildCandidates(50);
		/* Calculate scores. Lets assume that this is an external service that returns a list
		 * of Pair sorted descending by scores */
		List<Pair<String, Integer>> scores = calculateScores(candidates, process);
		/* Test if the list is ordered by scores */
		scores.stream()
				.reduce((previous, next) -> {
					assertTrue(
							next.getRight().compareTo(previous.getRight()) <= 0,
							"Unexpected scores order");
					return next;
				});
		/* A mostly naive test to confirm that the specific evaluation algorithm is correctly applied.
		 * just check if the total score is the sum of the common and the special evaluation algorithms */
		Map<String, Candidate> cmap = candidates.stream()
				/* Convert a candidate list to map for easy access. Assume that candidate name is unique */
				.collect(Collectors
						.toMap(
								Candidate::getName,
								Function.identity()));
		scores.stream()
				.forEach(score -> {
					Candidate candidate = cmap.get(score.getLeft()); /* Get the candidate by his name */
					/* If Function and not ToIntFunction is used you can try the andThen(...) and compose methods*/
					Integer expectedScore =
							ProcessEvaluationAlgorithms.common(candidate) + /* The common evaluation algorithm */
									specialEvaluation.applyAsInt(candidate); /* Apply the special evaluation algorithm */
					Assert.assertEquals(score.getRight(), expectedScore,
							"Unexpected score");
				});
	}

	/**
	 * Calculates the candidates scores
	 * @param candidates The candidates
	 * @param process The evaluation process
	 * @return the scores, a list of pairs (candidate name, score)
	 */
	/* PLEASE NOTE that the used algorithm is not the optimal and it may look naive on purpose,
	 * since this code also targets to show java 8 lamda and streams features
	 * (e.g. in this example we could simply return a Map with candidates and scores
	 * and not a sorted list of Pair) */
	protected List<Pair<String, Integer>> calculateScores(List<Candidate> candidates, IEvaluationProcess process) {
		/* Create a list of pair(candidate name, score) sorted by the score */
		return candidates.stream()
				/* Map each candidate to a pair of candidate name and the score that results
				 * from the evaluation process */
				.map(c -> {
					return Pair.of(
							c.getName(),
							process.evaluate(c));
				})
				/* In this case, the reversed() breaks the compiler's type inferencing mechanism,
				 * use lambda expression providing explicitly the type parameters */
				// .sorted(Comparator.comparing(Pair::getRight).reversed())
				.sorted(
						reverseOrder(
								comparing((Pair<String, Integer> p) -> p.getRight()))) /* sort the stream by score */
				.collect(Collectors.toList()); /* collect the stream into a list */
	}

}
