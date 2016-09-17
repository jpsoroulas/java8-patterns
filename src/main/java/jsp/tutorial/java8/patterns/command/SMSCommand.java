package jsp.tutorial.java8.patterns.command;

/**
 * The command for sending a SMS (the <b>ConcreteCommand2</b>).
 *
 * @author John Psoroulas
 */
public class SMSCommand implements Runnable {

	SMSDispatcher dispatcher;

	MessageData messageData;

	public SMSCommand(SMSDispatcher dispatcher, MessageData messageData) {
		this.dispatcher = dispatcher;
		this.messageData = messageData;
	}

	@Override
	public void run() {
		dispatcher.send(messageData.getMessage(), messageData.getRecipients());
	}

}
