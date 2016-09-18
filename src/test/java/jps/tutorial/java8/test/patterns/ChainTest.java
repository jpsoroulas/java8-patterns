package jps.tutorial.java8.test.patterns;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jps.tutorial.java8.test.TestSupport;
import jsp.tutorial.java8.patterns.chain.FirstLevelHDSupport;
import jsp.tutorial.java8.patterns.chain.HDSupport;
import jsp.tutorial.java8.patterns.chain.HDSupportHandlerAlgorithms;
import jsp.tutorial.java8.patterns.chain.SecondLevelHDSupport;
import jsp.tutorial.java8.patterns.chain.SupportRequest;
import jsp.tutorial.java8.patterns.chain.SupportRequest.RequestType;
import jsp.tutorial.java8.patterns.chain.ThirdLevelHDSupport;
import jsp.tutorial.java8.patterns.chain.UnsupportedRequestException;

/**
 * Chain pattern tests.
 *
 * @author John Psoroulas
 */
@Test(enabled = true)
public class ChainTest extends TestSupport {

	private static final Logger LOG = LoggerFactory.getLogger(ChainTest.class);

	/**
	 * Holds the 'old-way' chain of handlers.
	 */
	private HDSupport supportChain;

	/**
	 * Holds the 'lambda-way' chain of handlers.
	 */
	private Function<SupportRequest, SupportRequest> supportChainLambda;

	@BeforeClass
	public void setup() {
		/* ------------ The 'old-way' ------------ */
		//		/* Create the 'old way' support levels chain without streams */
		//		supportChain = new FirstLevelHDSupport(
		//				new SecondLevelHDSupport(
		//						new ThirdLevelHDSupport()));

		/* Create the 'old-way' support levels chain using streams, creating the handlers references explicitly,
		 * convenient for many handlers */
		//		supportChain = Arrays
		//				.stream(new HDSupport[] {new FirstLevelHDSupport(), new SecondLevelHDSupport(), new ThirdLevelHDSupport()})
		//				.reduce((f, n) -> { /* reduce to the handler that chains all handlers together */
		//					HDSupport target = f.getNextSupport();
		//					if(null == target) {
		//						target = f;
		//					}
		//					target.setNextSupport(n);
		//					return f;
		//				}).get();

		/* Another way to create the 'old-way' support levels chain using streams, using constructor references,
		 * convenient for many handlers */
		supportChain = Stream.<Supplier<HDSupport>> of(
				FirstLevelHDSupport::new,
				SecondLevelHDSupport::new,
				ThirdLevelHDSupport::new)
				.map(c -> c.get()) /* Create the handler instance */
				.reduce((f, n) -> { /* Reduce to the handler that chains all handlers together */
					HDSupport target = f.getNextSupport();
					if(null == target) {
						target = f;
					}
					target.setNextSupport(n);
					return f;
				}).get();

		/* ------------ The 'lambda-way' ------------ */
		/* Handler wrapper function that decides whether the actual handler should be invoked or not */
		UnaryOperator<Function<SupportRequest, SupportRequest>> handlerWrapper =
				(f) -> {
					return (r) -> {
						Validate.notNull(r, "Support request is required!");
						if(r.isHandled()) { /* Do not invoke the handler if the support request is handled */
							return r; /* Just return the already handled support request */
						}
						return f.apply(r); /* Otherwise invoke the handler */
					};
				};

		/* Create a final handler that throws an UnsupportedRequestException
		 * when the support request is not handled */
		Function<SupportRequest, SupportRequest> hanldedChecker =
				(r) -> {
					/* If the support request is finally handled, just return the request */
					if(r.isHandled()) {
						return r;
					}/* Otherwise, throw an exception */
					throw new UnsupportedRequestException(r.getType().toString());
				};

		/* Create the support levels chain with the 'lambda-way'*/
		supportChainLambda = Stream.<Function<SupportRequest, SupportRequest>> of(
				handlerWrapper.apply(r -> HDSupportHandlerAlgorithms.firstLevelSupport(r)),
				handlerWrapper.apply(r -> HDSupportHandlerAlgorithms.secondLevelSupport(r)),
				handlerWrapper.apply(r -> HDSupportHandlerAlgorithms.thirdLevelSupport(r)),
				hanldedChecker)
				/* reduce to the handler that chains all handlers together */
				//				.reduce((f, n) -> {
				//					return f.andThen(n);
				//				})
				//				.orElseGet(() -> Function.identity());
				/* A more elegant way to reduce */
				.reduce(Function.identity(), Function::andThen);

	}

	/**
	 * Tests a support request resolved by an 'old-way' support chain.
	 */
	public void submitResolvedSupportRequest() {
		/* This is the client that submits the support requests to support levels chain */
		/* Create a request that the second level support can handle */
		SupportRequest request = new SupportRequest(RequestType.LEVEL2);
		/* Submit the request to the support levels chain */
		supportChain.handle(request);
		Assert.assertTrue(request.isHandled(), "The request should be handled");
	}

	/**
	 * Tests a support request that can not resolved by an 'old-way' support chain.
	 */
	@Test(expectedExceptions = {UnsupportedRequestException.class})
	public void submitUnresolvedSupportRequest() {
		/* This is the client that submits the support requests to support levels chain */
		/* Create a request that none of the support levels can handle */
		SupportRequest request = new SupportRequest(RequestType.LEVEL4);
		/* Submit the request to the support levels chain */
		supportChain.handle(request);
	}

	/**
	 * Tests a support request resolved by a 'lambda-way' support chain.
	 */
	public void submitResolvedSupportRequestLambda() {
		/* This is the client that submits the support requests to support levels chain*/
		/* Create a request that the second level support can handle */
		SupportRequest request = new SupportRequest(RequestType.LEVEL2);
		/* Submit the request to the support levels chain */
		supportChainLambda.apply(request);
		/* Test the results */
		Assert.assertTrue(request.isHandled(), "The request should be handled");
	}

	/**
	 * Tests a support request that can not resolved by a 'lambda-way' support chain.
	 */
	@Test(expectedExceptions = {UnsupportedRequestException.class})
	public void submitUnresolvedSupportRequestLambda() {
		/* This is the client that submits the support requests to support levels chain*/
		/* Create a request that none of the support levels can handle */
		SupportRequest request = new SupportRequest(RequestType.LEVEL4);
		/* Submit the request to the support levels chain */
		supportChainLambda.apply(request);
	}
}
