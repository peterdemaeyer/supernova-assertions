# Conventions

## Casing conventions

Use UpperCamelCase for class names.
Use lowerCamelCase for method and field names.
Use UPPER_SNAIL_CASE for constants.
Use lower-dash-case (aka. kebab-case) for...
Use proper casing for brand names.
Camel-case abbreviations as if they were words.
Use plain sentence case to refer to objects.
Use proper formatting when referring to code, in particular classes, methods and fields.

Examples:

| Sentence case | Class name | Method name |
| XML output factory | `XmlOutputFactory` | `xmlOutputFactory` |
| IntelliJ | `IntelliJ` | `intelliJ` |
| WebLogic | `WebLogic` | `webLogic` |
| State-of-the-art object | `StateOfTheArtObject` | `stateOfTheArtObject` |

## Throwing exceptions

### Exception messages

Exception messages must be phrases, without capital at the start (unless it's a proper name) or punctuation at the end.
Think of an exception message as the continuation of a sentence "Operation failed because ...".
It is the caller's responsibility to incorporate the exception message in its own message and punctuate it appropriately at the end.

Exception messages must state what the problem is.
They should not say what the requirement is.

Exception messages must be parameterized when possible and appropriate.
The exception is for security reasons: do not include usernames or passwords in exception messages.

| Good | Bad |
| "parameter is null" | ~~"parameter must not be null"~~ |
| "parameter is negative: -5" | ~~"parameter is negative"~~ |

Rationale: stating the problem makes the code more generic because it does not require any assumptions be made about the requirements the caller may have.
Stating requirements 

### Exception types

Throw a `NullPointerException` when an argument is `null`, preferably using (statically imported) `Objects.requireNonNull()`. 

Throw an `IllegalArgumentException` when an argument is non-null.

## Code style

### Using static imports

Prefer static imports when the semantic of the imported symbol is clear enough from the name alone.

| Import                           | Static import       | Rationale                                                                                                             |
|----------------------------------|---------------------|-----------------------------------------------------------------------------------------------------------------------|
| ~~`StandardCharsets.UTF_8`~~     | `UTF_8`             | It is clear enough that UTF-8 is a charset. There is no need to overstate it by prefixing it with `StandardCharsets`. |
| ~~`Objects.requireNonNull()`~~   | `requireNonNull()`  | It is clear enough what `requireNonNull()` does. The fact that it's imported from `Object` is irrelevant.             |
| `XMLOutputFactory.newInstance()` | ~~`newInstance()`~~ | `newInstance()` on its own is not specific enough, it needs to be prefixed with `XMLOutputFactory`.                   | 
