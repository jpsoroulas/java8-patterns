## Factory pattern

The Factory pattern is one of the creational design patterns. The factory design pattern lets you create objects without
exposing the instantiation logic to the client. The created objects share a common interface used by the client to
interact with them. That is, the _factory_ creates one of a _family of objects_ and the client is not aware of
the object's specific subclass type. The instantiation of the appropriate object is based on the arguments
passed at the factory's object creation method.
One of the examples of this pattern in JDK, is the java.util.ResourceBundle class which returns the appropriate bundle
through the getBundle() method.

At the Factory pattern the following components are participating:
* The _factory_, creates one of a family of objects without exposing the instantiation logic to the client.
* The _family of objects_, the objects share a _common interface_ (interface or abstract class).

```
                                         family of objects
                                             +--------+
                                            /| type 1 |
                                           / +--------+
                                          /  +--------+
                                         / .'| type 2 |
+---------+ creates  +---------------+  /.'  +--------+
| Factory +--------->| <<int/abstr>> |<.-'        .
+---------+          +---------------+  \         .
                                         \        .
                                          `. +--------+
                                            \| type n |
                                             +--------+
```

### Test case
----

In our scenario, suppose that there is a company that constructs two computer models, the 'home' and the 'server' model.
The production of its model is based on the respective specifications that dictates what are the parts that the model consists of;
for example, the number of cores. disks and the memory. The client can order a computer by simply specifying the model
and not the specification details.

Adapting our scenario to the Factory pattern results to the following components mapping:
* Factory                              --->   ComputerFactory
* Family objects' common abstraction   --->   Computer
* Family object 1                      --->   HomeComputer
* Family object 2                      --->   ServerComputer

### Discussion
----

The Factory pattern implementation using lambdas differs from the old approach that the family objects' builders
(constructors, builder methods) can be lambdas that conforms to some interface and returns a new object.
Note that the lambda approach doesn’t scale very well if the factory method needs to take multiple arguments
to pass on to the object's 'builder'. In case of multiple arguments a custom functional interface should be
created with the respective parameterized types.