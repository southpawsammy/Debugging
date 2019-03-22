package numbers;

import java.util.Optional;

/* Identifies and parses legal floating point constants into Double
 * This class describes a floating chunk as either the fractional portion following
 * a decimal or the exponent portion
 */
public class FloatingPointParser {
	
	private static final FloatingPointParser INVALID_PARSER = new FloatingPointParser("");
	
	private static final DecimalInput DEFAULT_EXPONENT = new DecimalInput("1");
	private static final char EXPONENTIAL = 'e';

	private final DecimalInput base;
	private final Optional<DecimalInput> expo;
	
	private FloatingPointParser(String number) {
		if (number.contains(""+EXPONENTIAL)) {
			String[] numbers = number.split(""+EXPONENTIAL, 2);
			base = new DecimalInput(numbers[0]);
			expo = Optional.of(new DecimalInput(numbers[1]));
		} else {
			base = new DecimalInput(number);
			expo = Optional.empty();
		}
	}

	private DecimalInput getExpo() { return expo.orElseGet(() -> DEFAULT_EXPONENT); }

	public Double parseDouble() {
		assert this.isValidInput() : "Input not valid before parsing.";
		return Double.parseDouble(base+""+EXPONENTIAL+getExpo());
	}

	/* A floating point constant is valid if it contains one or two floating chunks and both
	 * the base and the integer exponent are valid. 
	 */
	public boolean isValidInput() {
		return containsAtLeastOneFloatingChunk() && hasValidBase() && hasValidIntegerExponent();
	}
	
	private boolean containsAtLeastOneFloatingChunk() {
		return !base.isInteger() || expo.isPresent();
	}
	
	private boolean hasValidBase() { return base.isValid(); }
	
	/* The exponent must be a valid decimal that represents an integer or not present */
	private boolean hasValidIntegerExponent() {
		return getExpo().isInteger() && getExpo().isValid();
	}
	
	public static final FloatingPointParser build (String number) {
		return number != null ? new FloatingPointParser(number) : INVALID_PARSER;
	}
}
