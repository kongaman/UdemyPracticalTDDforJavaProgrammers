package ex1.isbnValidator;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

	@Test
	void checkAValidISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.checkISBN(0140449116);
	}

}
