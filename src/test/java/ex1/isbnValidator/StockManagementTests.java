package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockManagementTests {

	@Test
	void testCanGetACorrectLocatorCode() {

		// This simulates the external webservice that is called in production code
		ExternalISBNDataService testWebService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
			}
		};

		// This simulates the external database that is called in production code
		ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {

			@Override
			public Book lookup(String isbn) {
				return null;
			}
		};

		StockManager stockmanager = new StockManager();
		// this sets our testWebService as the service to use for tests instead of the production webservice
		stockmanager.setWebService(testWebService);
		// this sets our testDatabaseService as the service to use for tests instead of the production database
		stockmanager.setDatabaseService(testDatabaseService);

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

}
