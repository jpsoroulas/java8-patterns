package jps.tutorial.java8.test.patterns;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.JobInfo;
import jsp.tutorial.java8.patterns.observer.CompanyJobNotificationSystem;
import jsp.tutorial.java8.patterns.observer.EngineeringDepartment;
import jsp.tutorial.java8.patterns.observer.JobNotificationSystem;
import jsp.tutorial.java8.patterns.observer.JobPorcessingAlgorithms;
import jsp.tutorial.java8.patterns.observer.LogisticsDepartment;
import jsp.tutorial.java8.patterns.observer.SalesDepartment;

/**
 * Observer pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class ObserverTest extends TestSupport {

  private static final Logger LOG = LoggerFactory.getLogger(ObserverTest.class);

  /**
   * Tests job notification for the 'old-way' listeners.
   */
  public void sendNotifications() {
    /* Create company's job notification system and register the respective departments
     * to get notified for new jobs */
    JobNotificationSystem ns = new CompanyJobNotificationSystem();
    /* Register the 'old-way' department listeners */
    ns.addListener(new EngineeringDepartment());
    ns.addListener(new LogisticsDepartment());
    ns.addListener(new SalesDepartment());
    doSendJobNotifications(ns);
  }

  /**
   * Tests job notification for the 'lambda-way' listeners.
   */
  public void sendNotificationsLambda() {
    /* Create company's job notification system and register the respective departments
     * to get notified for new jobs */
    JobNotificationSystem ns = new CompanyJobNotificationSystem();
    /* Register the 'lambda-way' department listeners */
    ns.addListener(JobPorcessingAlgorithms::engineering);
    ns.addListener(JobPorcessingAlgorithms::logistics);
    ns.addListener(JobPorcessingAlgorithms::sales);
    doSendJobNotifications(ns);
  }

  /**
   * Sends the notifications.
   */
  private void doSendJobNotifications(JobNotificationSystem ns) {
    /* Build the jobs information */
    List<JobInfo> jobsInfo = DataUtils.buildJobs(10);
    /* Notify the observers for the new jobs */
    jobsInfo.forEach(job -> ns.notify(job));
  }

}
