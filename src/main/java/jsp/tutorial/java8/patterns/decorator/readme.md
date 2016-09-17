### Decorator pattern

The Decorator pattern is one of the structural design patterns. It is used to dynamically attach additional behavior
to an object and provides a flexible alternative to subclassing for extending functionality.
It is used to dynamically extend the functionality of an object without having to change the original class
or using inheritance. This can be achieved by wrapping the original object, the _component_, in an object,
the _decorator_ that interacts with the client on behalf of the wrapped component, acting as a proxy.
The decorator comply with the component's interface. It receives all the requests from a client and forwards them
to the underlying component after or before adding new behavior to. This ensures that the new behavior can be added
to a given object externally at runtime without modifying its structure.
Decorator pattern prevents the proliferation of subclasses. It is easy to add any combination of capabilities.
The same capability can even be added many times. It is possible to have different decorator for a given component simultaneously.
Many examples of this pattern in JDK can be found at Java I/O classes. Just note that all subclasses of java.io.InputStream,
OutputStream, Reader and Writer have a constructor taking an instance of same type. The FileInputStream is an example of
a component of being decorated whereas the BufferedInputStream is an example of a decorator implementation.

At the Decorator pattern the following components are participating:
* The _Component_, the abstraction (interface/abstract) for the objects that additional behavior can be added to them dynamically.
* The _ConcreteComponent_, the component implementation. The object to which an additional behavior can be added.
* The _Decorator_, the base class of the decorators. It complies with the component interface and holds a reference to a component implementation.
* The _ConcreteDecorator(s)_, the decorator implementation(s) for adding behavior to the wrapped component.

```
                 +-------------+
                 |<<int/abstr>>|     refers
                 |  Component  | @-----------.
                 +-------------+              `--.
                      .^  ^._                     `.
                  _.-'       `-.                    :
               .-'              `-._                |
           _.-'                     `-._            |
+-------------------+           +-------------+     ;
|     <<class>>     |           |  <<class>>  |  ,-'
| ConcreteComponent |           |  Decorator  |-'
+-------------------+           +-------------+
                                    _.^ ^._
                               _..-'       `-.
                          _..-'               `-._
                      ..-'                        `-._
           +-------------------+           +-------------------+
           |     <<class>>     |           |     <<class>>     |
           | ConcreteDecorator1|    ...    | ConcreteDecoratorN|
           +-------------------+           +-------------------+
                             N implementations
```

In our scenario, suppose that a company builds web filters to restrict the internet access of its employees.
The applied web filters to an employee depends on the employee's rank. For example, the lower employee's rank
the higher internet access restriction.

Adapting our scenario to the Decorator pattern results to the following components mapping:
* Component            --->   WebFilter
* ConcreteComponent    --->   SimpleFilter
* ConcreteDecorator1   --->   WebMailsFilter
* ConcreteDecorator2   --->   NewsSitesFilter


The Decorator pattern implementation using lambdas differs from the old approach in how the component and the decorators
are implemented. At Java 8, the component/decorators implementation can be lambdas (Function<T, T>), whereas the
lambdas 'decoration' is achieved natively via the default method compose(...) of the Function interface.

```
+--------+ creates   +--------------+ <<compose>> ... +--------------+
| Client |.........> |Function<T, T>|.................|Function<T, T>|
+--------+           |   lambda1    |                 |   lambdaN    |
                     +--------------+                 +--------------+
                                      N Decorators
```