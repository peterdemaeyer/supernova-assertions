# Changes

## Since 1.0.0

* Added an `is` matcher implementing the identity matcher.
  When given a value, it corresponds to the `==` operator.
  When decorating another matcher, it corresponds to the identity operator, preserving its behavior while being a bit
  more descriptive.
* Added a `Matchers.sameAs` matcher implementing the identity relation corresponding to the `==` operator.
* Added a `Matchers.equalTo` matcher implementing equality relation corresponding to the `Object.equals` operator.
* Added a `Matchers.instanceOf` matcher implementing the relation corresponding to the `instanceof` operator.
* Added a `Assertions.assertThat` assertion builder.
