package jsp.tutorial.java8.patterns.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsp.tutorial.java8.patterns.JobInfo;

/**
 * The company's engineering department (the <b>ConcreteObserver1</b>), implements the
 * {@link JobListener} to receive notifications when a new job is available.
 *
 * @author John Psoroulas
 */
public class EngineeringDepartment implements JobListener {

  private static final Logger LOG = LoggerFactory.getLogger(EngineeringDepartment.class);

  @Override
  public void onNewJobAvailable(JobInfo job) {
    LOG.info("New job notification received by Engineering department");
    JobPorcessingAlgorithms.engineering(job);
  }

}
