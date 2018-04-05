package jsp.tutorial.java8.patterns.command;

/**
 * A container for the minimal required data for sending a message for both email and SMS.
 *
 * @author John Psoroulas
 */
public class MessageData {

	private String[] recipients;

	private String message;

	public MessageData(String message, String... recipients) {
		this.message = message;
		this.recipients = recipients;
	}

	public String getMessage() {
		return message;
	}

	public String[] getRecipients() {
		return recipients;
	}

}
