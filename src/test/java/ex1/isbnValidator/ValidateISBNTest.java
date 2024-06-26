package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
	void checkValid10DigitISBN() {
		boolean result = validator.checkISBN("0140449116");
		assertTrue(result, "first value");
		result = validator.checkISBN("0140177396");
		assertTrue(result, "second value");
	}

	@Test
	void checkValid13DigitISBN() {
		boolean result = validator.checkISBN("9780141023571");
		assertTrue(result, "first value");
		result = validator.checkISBN("9780141394886");
		assertTrue(result, "second value");
	}

	@Test
	void TenDigitISBNNumbersEndingWithXAreValid() {
		boolean result = validator.checkISBN("012000030X");
		assertTrue(result);
	}

	@Test
	void checkInvalid10DigitISBN() {
		boolean result = validator.checkISBN("0140449117");
		assertFalse(result);
	}

	@Test
	void checkInvalid13DigitISBN() {
		boolean result = validator.checkISBN("9780141023572");
		assertFalse(result);
	}

	@Test
	void nineDigitISBNIsNotAllowed() {
		assertThrows(NumberFormatException.class, () -> validator.checkISBN("123456789"));
	}

	@Test
	void nonNumericISBNsAreNotAllowed() {
		assertThrows(NumberFormatException.class, () -> validator.checkISBN("helloworld"));
	}
}
