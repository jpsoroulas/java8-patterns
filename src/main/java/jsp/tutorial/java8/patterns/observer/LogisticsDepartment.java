package jsp.tutorial.java8.patterns.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsp.tutorial.java8.patterns.JobInfo;

/**
 * The company's logistics department (the <b>ConcreteObserver3</b>), implements the
 * {@link JobListener} to receive notifications when a new job is available.
 *
 * @author John Psoroulas
 */
public class LogisticsDepartment implements JobListener {

  private static final Logger LOG = LoggerFactory.getLogger(LogisticsDepartment.class);

  @Override
  public void onNewJobAvailable(JobInfo job) {
    LOG.info("New job notification received by Logistics department");
    JobPorcessingAlgorithms.logistics(job);
  }

}
