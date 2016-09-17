package jsp.tutorial.java8.patterns.observer;

import jsp.tutorial.java8.patterns.JobInfo;
import jsp.tutorial.java8.patterns.JobInfo.JobCategory;

/**
 * Holds the departments processing algorithms when get notified about the new job.
 *
 * @author John Psoroulas
 */
public interface JobPorcessingAlgorithms {

	/**
	 * The job processing algorithm implementation for {@link EngineeringDepartment}.
	 *
	 * @param job the job information
	 */
	public static void engineering(JobInfo job) {
		/* Some dummy logic here, for example reject the job immediately for any job category but engineering */
		if(JobCategory.ENGINEERING != job.getJobCategory()) {
			return;
		}
		/* Some other logic here ... */
	}

	/**
	 * The job processing algorithm implementation for {@link LogisticsDepartment}.
	 *
	 * @param job the job information
	 */
	public static void logistics(JobInfo job) {
		/* Some dummy logic here, for example reject the job immediately for job category engineering */
		if(JobCategory.LOGISTICS == job.getJobCategory()) {
			return;
		}
		/* Some other logic here ... */
	}

	/**
	 * The job processing algorithm implementation for {@link SalesDepartment}.
	 *
	 * @param job the job information
	 */
	public static void sales(JobInfo job) {
		/* Some dummy logic here, for example reject the job immediately for job category logistics */
		if(JobCategory.LOGISTICS == job.getJobCategory()) {
			return;
		}
		/* Some other logic here ... */
	}

}
