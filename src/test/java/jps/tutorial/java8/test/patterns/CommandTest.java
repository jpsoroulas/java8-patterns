package jps.tutorial.java8.test.patterns;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.DataUtils;
import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.Candidate;
import jsp.tutorial.java8.patterns.ContactInfo;
import jsp.tutorial.java8.patterns.VariousUtils;
import jsp.tutorial.java8.patterns.command.EmailDispatcher;
import jsp.tutorial.java8.patterns.command.MessageData;
import jsp.tutorial.java8.patterns.command.MessageDispather;
import jsp.tutorial.java8.patterns.command.SMSDispatcher;

/**
 * Command pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class CommandTest extends TestSupport {

	private static final Logger LOG = LoggerFactory.getLogger(CommandTest.class);

	/**
	 * Tests the message dispatching for job evaluation results.
	 *
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void sendEvaluationResultsMessages() throws InterruptedException, ExecutionException {
		/*This is the client, creates the concrete commands and inject the receivers.
		 * Creates the invoker and bind the appropriate commands */

		/* Lets assume that 10 is the number of secretaries */
		int secretaries = 10;
		/* Create the 'invoker', this is the secretarial services supervisor in our domain model.
		 * The 'request' (send a message to inform the candidate about the evaluation result)
		 * is passed to the 'command' via the invoker's submit method */
		ExecutorService executor = Executors.newFixedThreadPool(secretaries);
		/* Create the 'receivers'. Assume that is thread safe, so a single instance
		 * can be used for the respective commands */
		MessageDispather emailDispatcher = new EmailDispatcher(); /* The 'receiver' for dispatching email messages*/
		MessageDispather smsDispatcher = new SMSDispatcher(); /* The 'receiver' for dispatching SMS messages*/

		/* The template message send at each candidate */
		String message = "Dear %s here is the results of the evaluation process blah, blah, blah, ...";
		/* Create the candidates, the same number as secretaries */
		List<Candidate> candiates = DataUtils.buildCandidates(secretaries);
		candiates.stream()
				.filter(candidate -> candidate.getContactInfo().isPresent()) /* Get the candidates that has set contact info */
				.map(candidate -> { /* Map each candidate with the future returned by the task submission to the executor */
					ContactInfo cinfo = candidate.getContactInfo().get();
					MessageData msgData = new MessageData(String.format(message, candidate.getName()), cinfo.getContact());
					/* Create the appropriate concrete command and inject the respective receivers */
					/* ------------ The old way ------------ */
					// Runnable command = cinfo.isEmail()
					//  ? new EmailCommand(emailDispatcher, msgData)
					//	: new SMSCommand(smsDispatcher, msgData);
					/* ------------ The lambda way ------------*/
					Runnable command = cinfo.isEmail()
							? () -> {
								emailDispatcher.send(msgData.getMessage(), msgData.getRecipients());
							}
							: () -> {
								smsDispatcher.send(msgData.getMessage(), msgData.getRecipients());
							};
					/* Bind the commands to the invoker and forward the request to the command */
					return executor.submit(command);
				})
				/* Do not forget to collect all the futures first and then 'query' them to retrieve the result.
				 * Otherwise the tasks are executed sequentially since each task submission
				 * is followed by the query to the returned 'future' that blocks the whole stream processing
				 * In case you want to query the future immediately after the task submission, you can have
				 * a minor performance improvement if you use parallel stream. Using parallel stream,
				 * the processed stream's elements in parallel are as many as the CPU cores of your desktop machine */
				.collect(Collectors.toList())
				.forEach(future -> {/* Check if the all commands are completed successfully, otherwise exceptions are raised */
					try {
						future.get(10, TimeUnit.SECONDS);
					}catch(Exception ex) {
						throw new RuntimeException(ex);
					}
				});
		/* Shutdown executor service */
		VariousUtils.shutdownExecutorService(executor, 10);
	}
}
