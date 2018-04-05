package jsp.tutorial.java8.patterns.observer;

import org.apache.commons.lang3.event.EventListenerSupport;

import jsp.tutorial.java8.patterns.JobInfo;

/**
 * The job notification system implementation for the company (the <b>ConcreteSubject</b>).
 *
 * @author John Psoroulas
 */
public class CompanyJobNotificationSystem implements JobNotificationSystem {

	/**
	 * This object holds the company's departments list interested in receiving notifications when a
	 * new job is available.
	 */
	private EventListenerSupport<JobListener> listeners = EventListenerSupport.create(JobListener.class);

	@Override
	public void addListener(JobListener listener) {
		listeners.addListener(listener);
	}

	@Override
	public void removeListener(JobListener listener) {
		listeners.removeListener(listener);
	}

	@Override
	public void notify(JobInfo info) {
		listeners.fire().onNewJobAvailable(info);
	}

}
