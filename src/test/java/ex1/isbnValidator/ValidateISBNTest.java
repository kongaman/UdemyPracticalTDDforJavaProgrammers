package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void checkValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN(140449116);
		assertTrue(result);
	}

	@Test
	void checkInvalidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN(140449117);
		assertFalse(result);
	}

}
