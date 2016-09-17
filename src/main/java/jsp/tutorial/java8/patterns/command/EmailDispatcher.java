package jsp.tutorial.java8.patterns.command;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsp.tutorial.java8.patterns.VariousUtils;

/**
 * The class that actually knows how to send a email message (the <b>Receiver1</b>).
 * The command pattern does not dictate the 'receivers' to have any common API.
 * It is just used for readability purposes.
 *
 * @author John Psoroulas
 */
public class EmailDispatcher extends MessageDispather {

	private static final Logger LOG = LoggerFactory.getLogger(EmailDispatcher.class);

	public void doSend(String message, String... recipients) {
		/* Some dummy implementation */
		Arrays.stream(recipients).forEach(
				recipient -> {
					LOG.info("Sending email to {}, : {}", recipient, message);
					VariousUtils.keepBusy(TimeUnit.MILLISECONDS, 10);
				});
	}
}
