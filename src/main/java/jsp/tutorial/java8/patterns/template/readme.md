### Template Method pattern

The Template Method Pattern is a behavior design patterns, it provides a template or a structure of an algorithm,
and lets subclasses to redefine certain steps of an algorithm without changing the algorithm’s structure.
The Template pattern suggests keeping the outline of the algorithm in a separate method referred to as
a _template method_ inside a class, which may be referred to as a _template class_, leaving out the specific
implementations of the variant portions of the algorithm to different subclasses of this class.
Examples of this pattern in JDK are the non-abstract methods of java.io.InputStream, java.io.OutputStream class.

At the Template Method pattern the following components are participating:
* The _Template class_, the class (abstract usually) that defines the template method.
* The _ConcreteTemplate(s)_, the template class implementations that redefines some portions of the template method.

```
+----------------------------------------+        +----------------------+
|  Template class                        |       /|   ConcreteTemplate1  |
+----------------------------------------+     .' +----------------------+
|  templateMethod() {                    |    /
|    step1();                            |   /
|    step2();                            | .'               .
|    aStep3(); // implementation needed  |<                 . N implementations
|    step4();                            |<                 .
|    aStep5(); // implementation needed  | `.
|  }                                     |   \
|                                        |    \
|  abstract aStep3();                    |     `. +----------------------+
|  abstract aStep5();                    |       \|   ConcreteTemplateN  |
+----------------------------------------+        +----------------------+

Note that the methods aStep3() and aStep5() are the steps that needs implementation.
The template class can have some default implementations

```

In our scenario, assume that a company is going to hire some people for various job positions.
The jobs are split into two types, the one that refers to software engineers and the other that refers to
assistant managers. The evaluation process for candidates is a combination of two evaluation steps. The first step
is common for both engineers and assistant managers job positions, whereas the second one depends on the job type.
The total score of the evaluation steps determines which candidates are suitable for the job position.

Adapting our scenario to the Strategy pattern results to the following components mapping:
* Template class      --->   EvaluationProcess (the evaluation process template)
* ConcreteTemplate1   --->   EngineerEvaluationProcess (evaluation process for Engineers)
* ConcreteTemplate2   --->   ManagerEvaluationProcess (evaluation process for Managers)


The Template Method pattern implementation using lambdas differs from the old approach that no subclasses are used.
The appropriate behavior with the form of lambda function, is injected to the constructor, moving this pattern from
polymorphism to composition.

```
+----------------------------------------+
|  Template class                        |
+----------------------------------------+
|  Template(aStep3Fun, aStep5Fun) {      |
|   ...                                  |
|  }                                     |
|                                        |
|  templateMethod() {                    |
|    step1();                            |
|    step2();                            |
|    // call aStep3Fun                   |
|    aStep3Fun.apply(...);               |
|    step4();                            |
|    // call aStep5Fun                   |
|    aStep5Fun.apply(...);               |
|  }                                     |
+----------------------------------------+

```