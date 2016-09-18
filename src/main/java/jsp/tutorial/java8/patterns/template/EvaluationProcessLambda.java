package jsp.tutorial.java8.patterns.template;

import java.util.function.ToIntFunction;

import jsp.tutorial.java8.patterns.Candidate;

/**
 * The class for job candidates evaluation process using lambda approach.
 *
 * @author John Psoroulas
 */
public class EvaluationProcessLambda implements IEvaluationProcess {

  private ToIntFunction<Candidate> specialEvaluation;

  public EvaluationProcessLambda(ToIntFunction<Candidate> specialEvaluation) {
    this.specialEvaluation = specialEvaluation;
  }

  public ToIntFunction<Candidate> getSpecialEvaluation() {
    return specialEvaluation;
  }

  /**
   * Evaluates the {@link Candidate} for a specific job type.
   *
   * @param candidate, the candidate
   * @param specialEvaluation the special evaluation lambda function depended on the job type
   * @return the candidate score
   */
  @Override
  public int evaluate(Candidate candidate) {
    return commonStep(candidate) + getSpecialEvaluation().applyAsInt(candidate);
  }

  /**
   * The first common evaluation step is expressed with an algorithm that considers the age of the
   * candidate.
   *
   * @param candidate the candidate
   * @return the candidate's score
   */
  protected int commonStep(Candidate candidate) {
    return ProcessEvaluationAlgorithms.common(candidate);
  }

}
