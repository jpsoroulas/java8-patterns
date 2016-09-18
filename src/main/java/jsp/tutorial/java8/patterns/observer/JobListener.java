package jsp.tutorial.java8.patterns.observer;

import jsp.tutorial.java8.patterns.JobInfo;

/**
 * The common API implemented by the company's departments (the <b>Observer</b>)
 * to get notified when a new job is available.
 *
 * @author John Psoroulas
 */
public interface JobListener {

  /**
   * Invoked when a new job is available with the specific information.
   *
   * @param info the job's information
   */
  public void onNewJobAvailable(JobInfo info);

}
