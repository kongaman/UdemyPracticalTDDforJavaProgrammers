package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	private ValidateISBN validator;

	@BeforeEach
	void setup() {
		validator = new ValidateISBN();
	}

	@Test
	void checkValidISBN() {
		boolean result = validator.checkISBN("0140449116");
		assertTrue(result, "first value");
		result = validator.checkISBN("0140177396");
		assertTrue(result, "second value");
	}

	@Test
	void checkInvalidISBN() {
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}
}
