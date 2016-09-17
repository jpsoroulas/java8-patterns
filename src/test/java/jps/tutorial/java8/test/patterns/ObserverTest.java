package jps.tutorial.java8.test.patterns;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.JobInfo;
import jsp.tutorial.java8.patterns.observer.CompanyJobNotificationSystem;
import jsp.tutorial.java8.patterns.observer.JobNotificationSystem;
import jsp.tutorial.java8.patterns.observer.JobPorcessingAlgorithms;

/**
 * Observer pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class ObserverTest extends TestSupport {

	private static final Logger LOG = LoggerFactory.getLogger(ObserverTest.class);

	private JobNotificationSystem notificationSystem;

	@BeforeClass
	public void setup() {
		/* Create company's job notification system and register the respective departments
		 * to get notified for new jobs */
		notificationSystem = new CompanyJobNotificationSystem();
		/* ------------ The 'old-way': ------------ */
		//		notificationSystem.addListener(new EngineeringDepartment());
		//		notificationSystem.addListener(new LogisticsDepartment());
		//		notificationSystem.addListener(new SalesDepartment());
		/* ------------ The 'lambda-way' ------------*/
		notificationSystem.addListener(JobPorcessingAlgorithms::engineering);
		notificationSystem.addListener(JobPorcessingAlgorithms::logistics);
		notificationSystem.addListener(JobPorcessingAlgorithms::sales);
	}

	/**
	 * Tests job notifications.
	 */
	public void sendJobNotifications() {
		/* Build the jobs information */
		List<JobInfo> jobsInfo = DataUtils.buildJobs(10);
		/* Notify the observers for the new jobs */
		jobsInfo.forEach(job -> notificationSystem.notify(job));
	}

}
