## Observer pattern

The Observer pattern is one of the behavioral design patterns. This pattern defines an one-to-many dependency
between objects so that when the 'one' object changes state, all its dependents, the 'many', are get notified.
The 'one' part of the dependency is the _subject_, whereas the 'many' part is the _observers_.
This pattern promotes the power of the loose coupling. Objects are loosely coupled when they interact but
they have very little knowledge of each other. That is, the only thing the subject knows about an
observer is that it implements a certain interface.
Examples of this pattern in JDK are all implementations of java.util.EventListener.

At the Observer pattern the following components are participating:
* The _Subject_, an abstraction (interface) for the subject implementations. This interface defines some
commonly used methods for the subject such as (un)registering and notifying observers.
* The _Observer_, an abstraction (interface) for the observers implementations. An interface with a
single method called by the subject when it's state changes.
* The _ConcreteSubject(s)_, the subject implementation(s), contains a list of observers to notify them when its state changes.
* The _ConcreteObserver(s)_, the observer implementation(s). Each observer registers itself with a concrete
subject to receive its updates.

```
+-----------------------+
|     <<interface>>     |                +----------------+
|        Subject        |                |  <<interface>> |
+-----------------------+@ 0..1    0..*  |    Observer    |
| subscribe(observer)   | \            @ +----------------+
| unsubscribe(observer) |  \         .'  | notify(state)  |
| notifyObservers()     |   \refers /    +----------------+
+-----------------------+    \     /             ^
            ^                 \  .'              |
            |                  \/                |
            |                 .'`.               |
            |                /    \              |
  +---------------------+   /      \             |
  |      <<class>>      | .'        \  +---------------------+
  |   ConcreteSubject   |/           \ |      <<class>>      |
  +---------------------+             \|   ConcreteObserver  |
  |  List of observers  |              +---------------------+
  +---------------------+
```

### Test case
----

In our scenario, suppose that a company has a system notification about the new internal job opportunities.
The system holds a list with company's departments which notified when a new internal job is available.
Each department implements its own policy with respect to new job positions. For example, the engineering
department consider only for jobs related to engineering.

Adapting our scenario to the Observer pattern results to the following components mapping:
* Subject             --->   JobNotificationSystem (the abstraction for a company's job system notification)
* Observer            --->   JobListener (the abstraction for departments to get notified for the new internal jobs opportunities)
* ConcreteSubject     --->   CompanyJobNotificationSystem (company's job system notification)
* ConcreteObserver1   --->   EngineeringDepartment (the company's engineering department)
* ConcreteObserver2   --->   SalesDepartment (the company's sales department)
* ConcreteObserver3   --->   LogisticsDepartment (the company's logistics department)

### Discussion
----

The Observer pattern implementation using lambdas differs from the old approach in how the various observers
are implemented. At the old approach, the observers are implemented via classes (or anonymous classes) whereas
at Java 8 the observers can be lambdas. Note that lambdas can not always express an observer.
The observers may be more complex, for example, they could have state and several methods.

```
+-----------------------+
|     <<interface>>     |
|        Subject        |
+-----------------------+
| subscribe(observer)   |
| unsubscribe(observer) |
| notifyObservers()     |
+-----------------------+
            ^
            |
            |
            |
  +---------------------+
  |      <<class>>      |
  |   ConcreteSubject   |     refers     +--------------+
  +---------------------+---------------@|    lambda    |
  |  List of observers  |           0..* +--------------+
  +---------------------+
```