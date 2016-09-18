### Strategy Pattern

----
#### Description
----

The Strategy pattern is one of the behavioral design patterns. It is used to define a family of algorithms,
encapsulate each one behind a common programming interface, and make them interchangeable dynamically.
This pattern lets the algorithm vary independently from clients that use it. It converts the generalization
of the template method to composition or aggregation.
It allows the algorithm to vary frequently and is a very good alternate solution to sub-classing.
One of the examples of this pattern in JDK, is Collections.sort() method that takes Comparator parameter with
different implementations of Comparator interfaces.

At the Strategy pattern the following components are participating:
* The _Strategy_, an abstraction (interface) of the strategies via an interface or an abstract class.
* The _ConcreteStrategy(ies)_, the strategy(ies) implementation(s) that contains the algorithm specific implementation.
* The _Context_, the class which uses the Strategy.

```
                                                             +--------------------+
                                                            .'      <<Class>>     |
                              +--------------------+     .-' |  ConcreteStrategy1 |
                              |    <<interface>>   |   .'    +--------------------+
+--------------+     refers   |      Strategy      |<-'                .
|   Context    |-------------@+--------------------+<.                 .   N implementations
+--------------+              |       apply()      |  `.               .
                              +--------------------+    `-.  +--------------------+
                                                           `.|      <<Class>>     |
                                                             |  ConcreteStrategyN |
                                                             +--------------------+
```

----
#### Test case
----

In our scenario, there is an employer that is going to hire some people, the candidates, for a job position.
Each candidate holds some degrees which will be evaluated by the employer in order to select the appropriate
candidate for the job. The selection algorithm may vary, for example could be the degree's relevance with the field
of the offering job position, the grade of the degree, the number of the degrees or a mix of the above.
In our case two hiring strategies will be used:
* one that the algorithm is based on the 'relevant degree' and the
* other is based on the grade.

Adapting our scenario to the Strategy pattern results to the following components mapping:
* Context             --->   Employer (the employer)
* Startegy            --->   HiringStrategy (hiring strategy common API)
* ConcreteStartegy1   --->   DegreeRelevantHiringStrategy ('relevant degree' hiring strategy)
* ConcreteStartegy2   --->   GradeHiringStrategy ('grade based' hiring strategy)

----
#### Discussion
----

The Strategy pattern implementation using lambdas differs from the old approach in how the various strategies
are implemented. At the old approach, the strategies are implemented via classes (or anonymous classes) whereas
using lambdas the strategies are implemented with lambdas. Rather than creating a hierarchy of classes to
support the strategy pattern we can now create a library of lambda functions or static methods (used as method
references) to apply the respective strategy.

```
                                                              +------------+
                                                            .'|   lambda1  |
                              +--------------------+     .-'  +------------+
                              |    <<interface>>   |   .'           .
+--------------+    refers    |      Strategy      |<-'             . N lambdas
|   Context    |-------------@+--------------------+<.              .
+--------------+              |       apply()      |  `.
                              +--------------------+    `-.  +------------+
                                                           `.|   lambdaN  |
                                                             +------------+
```