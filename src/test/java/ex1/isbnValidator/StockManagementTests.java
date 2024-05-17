package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockManagementTests {

	@Test
	void testCanGetACorrectLocatorCode() {
		String isbn = "0140177396";
		StockManager stockmanager = new StockManager();
		String locatorCode = stockmanager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

}
