package jsp.tutorial.java8.patterns.command;

import java.util.Arrays;

import org.apache.commons.lang3.Validate;

/**
 * A common API for {@link SMSDispatcher} and {@link EmailDispatcher}.
 * This is not prerequisite for the command pattern since a 'receiver' could
 * be any object. This base class is added only for code readability and DRY.
 *
 * @author John Psoroulas
 */
public abstract class MessageDispather {

	public void send(String message, String... recipients) {
		// Perform some validation
		Validate.notNull(message, "Undefined message!");
		Validate.notEmpty(recipients, "At least one recipient should be specified");
		Arrays.stream(recipients).forEach(Validate::notBlank);

		// Call the actual functionality
		doSend(message, recipients);
	}

	protected abstract void doSend(String message, String... recipients);

}
