# Incompatible changes since 2.0.0

* Bumped minimum binary compatibility to Java 11.

# Incompatible changes since 2.0.0

* Replaced the `assertThat(subject).matches(matcher)` paradigm with the `assertThat(subject, matches(matcher))`
  paradigm, eliminating the need for a `Assertion` class.

# Changes since 2.0.0

* Added default assertions of the form `Assertions.assertThat(condition)`.
* Added a method `Assertions.fail` that fails instantly.
* Added a `Matchers.is` matcher implementing the "is" relation for primitive expected values using the `==` operator.
  Beware that this may behave differently than for the corresponding object values.
  For example: primitive `1L == 1` is `true` while object `Long.valueOf(1L) == Integer.valueOf(1)` is `false`.
* Added a `Matchers.nan` matcher matching NaN values.
  Beware that the `==` behaves differently for primitive NaN values than for corresponding object values.
  For example: primitive `Double.NaN == Double.NaN` is `false` while object `Double.valueOf(Double.NaN) == 
  Double.valueOf(Double.NaN)` is `true`.
* Added a `Matchers.closeTo` matcher matching numbers with a tolerance.
  The expected value defines the type, the tolerance and actual value must be of the same or a compatible type.
* Added `Matchers.not` and `Matchers.does` matchers to negate other matchers. 

# Changes since 1.1.0

* Added a `regex` matcher implementing a regular expression matcher for `CharSequence`.
* Added a `matches` matcher decorator which is similar to `is` but allows for writing more intuitive assertions in
  combination with matchers such as the `regex` matcher. Example: `assertThat(actual).matches(regex(".*"))`.

# Changes since 1.0.0

* Added an `is` matcher implementing the identity matcher.
  When given a value, it corresponds to the `==` operator.
  When decorating another matcher, it corresponds to the identity operator, preserving its behavior while being a bit
  more descriptive.
* Added a `Matchers.sameAs` matcher with the same semantic as the `is` matcher but allowing a more explicit assertion
  `assertThat(actual).is(sameAs(expected))`.
* Added a `Matchers.is` matcher implementing the identity relation corresponding to the `==` operator.
* Added a `Matchers.equalTo` matcher implementing equality relation corresponding to the `Object.equals` operator.
* Added a `Matchers.instanceOf` matcher implementing the relation corresponding to the `instanceof` operator.
* Added a `Assertions.assertThat` assertion builder.
