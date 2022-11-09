# Supernova matchers

Supernova Matchers allow users to write declarative assertions.
Inspired by Hamcrest, its purpose is virtually identical.
However, Hamcrest has not evolved recently, and attempts to contribute have failed.
To fill that gap, Supernova Matchers was born.

The main philosophy:
* JUnit is a truly great prohect, but the expected argument before the actual is counterintuitive.
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

| JUnit 5                           | Supernova Matchers                              |
|-----------------------------------|-------------------------------------------------|
| `assertSame(expected, actual)`    | `assertThat(actual, is(matcher(expected)))`      |
| `assertNotSame(expected, actual)` | `assertThat(actual, is(not(matcher(expected))))` |
