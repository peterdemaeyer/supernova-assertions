# Supernova matchers

Supernova Matchers is a minimalistic framework that allow users to write semantically correct declarative assertions.
The API consists of all public and protected classes in package `su.pernova`.
The API follows semantic versioning.
Incompatible API changes are done in major versions.
Compatible API changes are done in minor versions.
Any other changes such as bugfixes are done in patch versions.

The public classes in package `internal.su.pernova` are _not_ part of the API and are subject to change.
The main classes are:

| Class        | Description                                                    |
|--------------|----------------------------------------------------------------|
| `Assertions` | Utility class providing static factory methods for assertions. |
| `Subjects`   | Utility class providing static factory methods for subject.    |
| `Matchers`   | Utility class providing static factory methods for matchers.   |

# Usage

| Example                                    | Description                                                                                               |
|--------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| `assertThat(actual).is(expected)`          | Asserts object identity, corresponding to the `==` operator.                                              |
| `assertThat(actual).is(null)`              | Asserts whether an object is null. This is nothing more than a particular case of the identity operator.  |
| `assertThat(actual).is(true)`              | Asserts whether a condition is true This is nothing more than a particular case of the identity operator. |
| `assertThat(actual).is(sameAs(expected))`  | Equivalent to `assertThat(actual).is(expected)`, but more explicit, also in the message upon mismatch.    |
| `assertThat(actual).is(equalTo(expected))` | Asserts object equality, corresponding to the `Object.equals` operator.                                   |
| `assertThat(actual).is(instanceOf(class))` | Asserts object class, corresponding to the `instanceof` operator.                                         |
| `assertThat(subject(actual)).is(expected)` | Equivalent to `assertThat(actual).is(expected)`, but more explicit.                                       |

# History

Inspired by Hamcrest, its purpose is virtually identical.
However, Hamcrest has not evolved recently, and attempts to contribute have failed.
To fill that gap, Supernova Matchers was born.

The main philosophy:

* JUnit is great, but the expected argument before the actual one when writing assertions is counterintuitive.
  Who hasn't accidentally mixed up expected and actual arguments when they first started with JUnit?
  In the same spirit as Hamcrest, Supernova Matchers puts the actual argument before the expected.
* JUnit's assertions are not flexible.
  New assertions must be added to the `Assertions` class and are not customizable.
  In the same spirit as Hamcrest, Supernova Matchers offers common matchers, but allows customization.

So, what are Supernova Matchers doing better than Hamcrest?

* Compiled for Java 1.8+.
* Correct semantic difference between object identity and object equality.
* ...

# Migration from Hamcrest

| Hamcrest                              | Supernova Matchers       |
|---------------------------------------|--------------------------|
| `org.hamcrest.`                       | `su.pernova.matchers.`   |
| `CoreMatchers.nullValue()`            | `Matchers.is(null)`      |
| `CoreMatchers.notNullValue()`         | `Matchers.is(not(null))` |
| `CoreMatchers.sameInstance(expected)` | `Matchers.is(expected)`  |

# Migration from JUnit 5

| JUnit 5                           | Supernova Matchers                     |
|-----------------------------------|----------------------------------------|
| `assertSame(expected, actual)`    | `assertThat(actual).is(expected)`      |
| `assertNotSame(expected, actual)` | `assertThat(actual).is(not(expected))` |
