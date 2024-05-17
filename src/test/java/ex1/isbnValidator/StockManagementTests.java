package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockManagementTests {

	@Test
	void testCanGetACorrectLocatorCode() {

		// This simulates the external webservice that is called in production code
		ExternalISBNDataService testService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
			}
		};

		StockManager stockmanager = new StockManager();
		stockmanager.setService(testService);

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

}
