## Design Patterns with Java 8

The purpose of these examples is to show how some of the traditional _Design Patterns_ can be applied using
[Lambdas expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html).
Each example is accompanied by a short description of the examined pattern and a brief discussion demonstrating
the differences between the implementation with and without the Lambda expressions.

Currently, the examined patterns are the following:

##### Creational Design Patterns
* [Factory](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns/factory)

##### Behavioral Design Patterns
* [Strategy](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns//strategy)
* [Template method](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns/template)
* [Observer](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns/observer)
* [Chain of Responsibility](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns/chain)
* [Command](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns/command)

##### Structural Design Patterns
* [Decorator](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns/decorator)

----

The project is built and follows the standard structure of a [Maven](https://maven.apache.org/) project,
so the domain model and the tests of the demonstrated examples can be found at
[main/java](https://github.com/jpsoroulas/java8-patterns/tree/master/src/main/java/jsp/tutorial/java8/patterns) and
[test/java](https://github.com/jpsoroulas/java8-patterns/tree/master/src/test/java/jps/tutorial/java8/test/patterns) respectively.

You can compile and run the tests by typing the following command at the root project directory,

```
mnv compile test
```

----

Written by John Psoroulas, 2016.