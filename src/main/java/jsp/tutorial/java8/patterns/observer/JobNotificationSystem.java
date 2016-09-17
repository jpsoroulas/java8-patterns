package jsp.tutorial.java8.patterns.observer;

import jsp.tutorial.java8.patterns.JobInfo;

/**
 * The abstraction for the Job system notification (the <b>Subject</b>).
 *
 * @author John Psoroulas
 */
public interface JobNotificationSystem {

	/**
	 * Registers the specific listener/observer to receive notifications when a new internal job is
	 * available.
	 *
	 * @param listener the listener
	 */
	void addListener(JobListener listener);

	/**
	 * Unregisters the specific listener/observer from receiving notifications when a new internal job
	 * is available.
	 *
	 * @param listener the listener
	 */
	void removeListener(JobListener listener);

	/**
	 * Notifies the registered listeners about the new internal job.
	 *
	 * @param info contains information about the job
	 */
	void notify(JobInfo info);

}
