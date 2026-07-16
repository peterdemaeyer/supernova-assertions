package internal.su.pernova.assertions.matchers;

import static java.lang.String.format;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.is;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.MatcherContractTest;

class IsDoubleTest implements MatcherContractTest {

	@Override
	public IsDouble getInstance() {
		return assertInstanceOf(IsDouble.class, is(8.5));
	}

	@Test
	void decimalNumberMatchesDouble() {
		assertThat(5.2, is(5.2d));
		// Pick a float carefully so that we don't suffer from precision loss.
		assertThat(5.5f, is(5.5d));
		assertThat(BigDecimal.valueOf(5.31e123d), is(5.31e123d));
	}

	@Test
	void integerNumberMatchesDouble() {
		assertThat(-984985465165L, is(-984985465165d));
		assertThat(-56, is(-56d));
		assertThat((short) -1000, is(-1000d));
		assertThat((byte) 75, is(75d));
		assertThat(BigInteger.valueOf(-685464L), is(-685464d));
	}

	@Test
	void characterMatchesDouble() {
		assertThat('T', is(84d));
		// '倀' is decimal 20480.
		assertThat('倀', is(20480d));
	}

	@Test
	void characterDoesNotMatchDouble() {
		assertEquals(format("expected that subject is: 10.0%nbut was: 'z'"),
				assertThrows(AssertionError.class,
						() -> assertThat('z', is(10d))).getMessage());
	}

	@Test
	void doubleDoesNotMatchDouble() {
		assertEquals(format("expected that subject is: 6.0%nbut was: 5.0"),
				assertThrows(AssertionError.class,
						() -> assertThat(5d, is(6d))).getMessage());
	}

	@Test
	void floatDoesNotMatchDouble() {
		assertEquals(format("expected that subject is: 6.0%nbut was: 5.0"),
				assertThrows(AssertionError.class,
						() -> assertThat(5f, is(6d))).getMessage());
	}

	@Test
	void nullDoesNotMatchDouble() {
		assertEquals(format("expected that subject is: 6.0%nbut was: null"),
				assertThrows(AssertionError.class,
						() -> assertThat(null, is(6d))).getMessage());
	}

	@Test
	void objectDoesNotMatchDouble() {
		final Object object = new Object();
		assertEquals(format("expected that subject is: 6.0%nbut was: \"%s\"", object),
				assertThrows(AssertionError.class,
						() -> assertThat(object, is(6d))).getMessage());
	}

	@Test
	void positiveZeroMatchesNegativeZero() {
		assertDoesNotThrow(() -> assertThat(-0d, is(0d)));
		assertDoesNotThrow(() -> assertThat(-0f, is(0d)));
	}

	@Test
	void floatInfinityMatchesDoubleInfinity() {
		assertDoesNotThrow(() -> assertThat(Float.NEGATIVE_INFINITY, is(Double.NEGATIVE_INFINITY)));
		assertDoesNotThrow(() -> assertThat(Float.POSITIVE_INFINITY, is(Double.POSITIVE_INFINITY)));
	}

	@Test
	void stringValue() {
		assertEquals("is(5.7)", is(5.7).toString());
	}
}
