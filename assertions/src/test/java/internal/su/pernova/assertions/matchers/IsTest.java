package internal.su.pernova.assertions.matchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;

import java.util.Date;

import org.junit.jupiter.api.Test;

import su.pernova.assertions.Context;
import su.pernova.assertions.Matcher;
import su.pernova.assertions.MatcherContractTest;
import su.pernova.assertions.MatcherFactory;
import su.pernova.assertions.MatcherFactoryContractTest;

class IsTest implements MatcherContractTest {

	@Override
	public Is getInstance() {
		return assertInstanceOf(Is.class, is(equalTo(new Object())));
	}

	@Test
	void stringValue() {
		assertEquals("is{equalTo(abc)}", is(equalTo("abc")).toString());
	}

	@Test
	void significanceOfParentheses() {
		final Date date1 = new Date(1_000_000_000_000L);
		final Date date2 = new Date(2_000_000_000_000L);
		final Matcher matcher1 = is(equalTo(date1).or(date2)).contextualize(new Context());
		assertTrue(matcher1.match(new Date(1_000_000_000_000L)));
		assertTrue(matcher1.match(new Date(2_000_000_000_000L)));

		final Matcher matcher2 = is(equalTo(date1)).or(date2).contextualize(new Context());
		assertTrue(matcher2.match(new Date(1_000_000_000_000L)));
		assertFalse(matcher2.match(new Date(2_000_000_000_000L)));
		assertTrue(matcher2.match(date2));
	}

	static class MatcherFactoryTest implements MatcherFactoryContractTest {

		@Override
		public MatcherFactory getInstance() {
			return Is.getMatcherFactory(null);
		}
	}
}
