package jsp.tutorial.java8.patterns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author John Psoroulas
 */
public final class VariousUtils {

	private static final Logger LOG = LoggerFactory.getLogger(VariousUtils.class);

	private VariousUtils() {
	}

	public static void keepBusy(TimeUnit unit, long time) {
		try {
			unit.sleep(time);
		}catch(InterruptedException e) {
			/* Restore threads interruption status */
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Shutdown the executor after waiting for the specified timeout (in ms)
	 * @param executor
	 * @param timeout
	 */
	public static void shutdownExecutorService(ExecutorService executor, long timeout) {
		shutdownExecutorService(executor, timeout, TimeUnit.MILLISECONDS, 1);
	}

	public static void shutdownExecutorService(ExecutorService executor, long timeout, TimeUnit units, int maxAttempts) {
		/* Disable new tasks from being submitted and waits currently running tasks to terminate */
		executor.shutdown();
		try {
			int attempt = 0;
			do {
				LOG.info("Awaiting tasks completion ...");
				attempt++;
				/* Wait a while for existing tasks to terminate */
			}while(attempt <= maxAttempts && !executor.awaitTermination(timeout, units));
			executor.shutdownNow();/* Cancel currently executing tasks */
		}catch(InterruptedException e) {
			executor.shutdownNow();/* (Re-)Cancel if current thread also interrupted */
			Thread.currentThread().interrupt();/* Preserve interrupt status */
		}
	}

}
