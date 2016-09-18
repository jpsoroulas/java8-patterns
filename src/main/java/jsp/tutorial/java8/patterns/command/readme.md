## Command pattern

The Command pattern is one of the behavioral design patterns and it is used to implement loose coupling
between the _invoker_ and the _receiver_ of a request. The invoker is the object that issues a request
on behalf of the client. The request is received by an implementation of the request
processing abstraction, the _command_. The request processing abstraction is expressed by an interface
with no-args method, usually named as _execute_. Each command implementation encapsulates the appropriate
receiver which is the one that actually knows how to implement the request.
The client program creates the command and probably binds it with the appropriate receiver
(if it is not already encapsulated). After that creates the invoker and injects the command to perform an action.
Note that the command may support undo. The command’s execute operation can store state for reversing its
effects in the command itself. In this case, the command interface must have an undo operation that
reverses the effects of the previous command execution.
One of the example of this pattern in JDK, is the java.lang.Runnable and javax.swing.Action interfaces.

At the Command pattern the following components are participating:
* The _Command_, an abstraction (interface) for the command implementations, defines a common API for
executing an operation.
* The _ConcreteCommand(s)_, the command implementations. Defines a binding between a receiver and an action
by invoking the receiver's appropriate method.
* The _Invoker_, forwards the request from client to the command object.
* The _Receiver_, knows how to process the request.
* The _Client_, creates the concrete command and injects its receiver. Creates the invoker, inject
the appropriate command and execute the invoker's method to issue the request at the command.

```
                                          +-------------+
+------------+       +------------+ refers|<<Interface>>|
|  Client    |       |  Invoker   |------@|  Command    |
+------------+       +------------+       +-------------+
      :  :                                | execute()   |
      :  :                                +-------------+
      :  : creates                              ^
      :  :                                      |
      :  :                                      |
      :  :           +------------+       +-----------------+
      :  :..........>|  Receiver  | calls |    <<class>>    |
      :              |------------|<<<<<<<| ConcreteCommand |
      :              |  action()  |       +-----------------+
      :              +------------+       |    execute()    |
      :..................................>|                 |
                                          +-----------------+
```

### Test case
----

In our scenario, suppose that a company has to inform the candidates of a job position about the evaluation
results. A candidate is informed either via an email or a SMS message depending on candidate's preference.
The message dispatching is performed by the company's secretaries which coordinated by the secretarial services
supervisor.

Adapting our scenario to the Command pattern results to the following components mapping:
* Client             --->   CommandTest -the unit test- (company/secretarial services)
* Invoker            --->   ExecutorService (secretarial services supervisor, each thread represents a secretary)
* Command            --->   java.lang.Runnable interface
* ConcreteCommand1   --->   EmailCommand (command to inform candidate vi email)
* ConcreteCommand2   --->   SMSCommand (command to inform candidate vi SMS)
* Receiver1          --->   EmailDispatcher (the receiver for sending an email)
* Receiver2          --->   SMSDispatcher (the receiver for sending a SMS)

### Discussion
----

The Command pattern implementation using lambdas differs from the old approach in how the various commands
are implemented. At the old approach, the commands are implemented via classes (or anonymous classes) whereas
at Java 8 the commands can be lambdas (assuming the 'undo' functionality is not implemented since it requires
to keep state).

```
+------------+       +------------+
|  Client    |       |  Invoker   |`.
+------------+       +------------+  \
      :  :                            `.
      :  :                              `. refers
      :  : creates                        `.
      :  :                                  \
      :  :                                   `.
      :  :           +------------+            `@
      :  :..........>| Receiver   | calls +-------------+
      :              |------------|<<<<<<<|             |
      :              |  action()  |       |   lambda    |
      :              +------------+       |             |
      :..................................>|             |
                                          +-------------+
```


