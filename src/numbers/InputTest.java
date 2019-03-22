package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.TestHook;

/** Some example tests of parser **/
public class InputTest {

	// For using hook methods that are not object-specific
	private static final TestHook hook = new DecimalInput("").new TestHook();

	/** hasValidMiddlePadding tests **/
	/* Example: 1_234 -> valid */
	@Test
	public void test_padding_nominal() {
		assertTrue(hook.hasValidMiddlePadding("1_234"));
	}

	/* Example: 1__234 -> valid */
	@Test
	public void test_padding_long_underscore() {
		assertTrue(hook.hasValidMiddlePadding("1__234"));
	}

	/* Example: 12_34 -> invalid */
	@Test
	public void test_padding_bad_underscore() {
		assertFalse(hook.hasValidMiddlePadding("12_34"));
	}

	/* Example: _1_234 -> invalid */
	@Test
	public void test_padding_leading_underscore() {
		assertFalse(hook.hasValidMiddlePadding("_1_234"));
	}
}
