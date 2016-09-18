## Chain of Responsibility pattern

The Chain of Responsibility pattern is a behavior pattern in which a group of objects is chained together
and is responsible to handle a request. The client pass the request to the chain of objects; If an object
in the chain can process the particular request, it does so and returns the corresponding response.
Otherwise, it delegates the request to the next object in the chain.
This pattern is used to avoid coupling the sender of a request to its receiver by giving more than one object
a chance to handle the request and It allows you to dynamically change the members or the order of the chain.
Only one object in the chain handles a request, whereas some requests might not get handled. Those restrictions,
of course, are for a classic CoR implementation. In practice, those rules are bent; for example, in JEE,
the servlet filters are a CoR implementation that allows multiple filters to process an HTTP request
(avax.servlet.Filter#doFilter()).

At the Chain of Responsibility the following components are participating:
* The _Handler_, an abstraction (interface/abstract) of the objects that may handle the requests.
* The _ConcreteHandler(s)_, the Handler's implementation(s) for processing the request that is responsible for.
* The _Client_, submits the request to the first handler on the chain.

<pre>
+--------+ creates  +-------------+
| Client |.........>|<<int/abstr>>|
+--------+          |   Handler   |
                    +-------------+
                        ^    ^
                      .'      `.
                    .'          `.
    +----------------+           +----------------+
    |  <<class>>     |    ...    |  <<class>>     |
    |ConcreteHandler1|           |ConcreteHandlerN|
    +----------------+           +----------------+
                      N Handlers
</pre>

### Test case
----

In our scenario, suppose that a software company offers three levels of help desk support with the following order:
the first level, the second level and the third level support. A support level will try to handle the request, if
all the lower-ordered support levels fail.

Adapting our scenario to the Chain of Responsibility pattern results to the following components mapping:
* Client             --->   ChainTest -the unit test- (the client)
* Handler            --->   HDSupport
* ConcreteHandler1   --->   FirstLevelHDSupport
* ConcreteHandler2   --->   SecondLevelHDSupport
* ConcreteHandler3   --->   ThirdLevelHDSupport

### Discussion
----

The Chain of Responsibility implementation using lambdas differs from the old approach in how the various handlers
are implemented and propagate the request to the next handler. At Java 8, the handlers implementation can be lambdas
(Function<T, T>), whereas the lambdas chaining is achieved natively via the default method andThen(...) of
the Function interface.

<pre>
+--------+ creates   +--------------+ <<andThen>> ... +--------------+
| Client |.........> |Function<T, T>|.................|Function<T, T>|
+--------+           |   lambda1    |                 |   lambdaN    |
                     +--------------+                 +--------------+
                                      N Handlers
</pre>