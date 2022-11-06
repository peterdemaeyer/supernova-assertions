# Changes

## Since 1.0.0

* Added a `notANumber` matcher implementing the "not a number" relation based on the `isNaN` operator.
  It applies to instances of `Double` and `Float`.
* Added an `is` matcher implementing the identity relation.
  When given a value, it corresponds to the "==" operator.
  When decorating another matcher, it corresponds to the identity operator, preserving its behavior while being a bit
  more descriptive.
* Added `sameAs` matcher implementing the "same as" relation corresponding to the "==" operator.
