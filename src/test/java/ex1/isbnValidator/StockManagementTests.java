package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class StockManagementTests {

//	@Test
//	void testCanGetACorrectLocatorCode() {
//
//		// This simulates the external webservice that is called in production code
//		ExternalISBNDataService testWebService = new ExternalISBNDataService() {
//
//			@Override
//			public Book lookup(String isbn) {
//				return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
//			}
//		};
//
//		// This simulates the external database that is called in production code
//		ExternalISBNDataService testDatabaseService = new ExternalISBNDataService() {
//
//			@Override
//			public Book lookup(String isbn) {
//				return null;
//			}
//		};
//
//		StockManager stockmanager = new StockManager();
//		// this sets our testWebService as the service to use for tests instead of the production webservice
//		stockmanager.setWebService(testWebService);
//		// this sets our testDatabaseService as the service to use for tests instead of the production database
//		stockmanager.setDatabaseService(testDatabaseService);
//
//		String isbn = "0140177396";
//		String locatorCode = stockmanager.getLocatorCode(isbn);
//		assertEquals("7396J4", locatorCode);
//	}

	@Test
	void testCanGetACorrectLocatorCode() {

		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));

		StockManager stockmanager = new StockManager();
		stockmanager.setWebService(webService);
		stockmanager.setDatabaseService(databaseService);

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);
		// this creates mocks of the 2 services with default implementations (will return a new book with all values
		// (isbn, author, title) set to null)

		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		// when lookup is called on databaseService then return...
		// real values for book don't matter because we want to test which method (database or webservice) is used to
		// get the data.

		StockManager stockmanager = new StockManager();
		stockmanager.setWebService(webService);
		stockmanager.setDatabaseService(databaseService);

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);

		// verify(databaseService, times(1)).lookup("0140177396");
		// times(1) is the default so we don't need that parameter if we check for a 1 time call
		verify(databaseService).lookup("0140177396");
		// check that databaseService-class has been called 1 time with the method lookup and the parameter "0140177396"
		// verify(webService, times(0)).lookup(anyString());
		// instead of times(0) we can use never() to make it even more clear that this method should never be called
		verify(webService, never()).lookup(anyString());
		// check that webService-Class has been called 0 times with the method lookup and any string as parameter.
	}

	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
		ExternalISBNDataService databaseService = mock(ExternalISBNDataService.class);
		ExternalISBNDataService webService = mock(ExternalISBNDataService.class);

		when(databaseService.lookup(anyString())).thenReturn(null);
		when(webService.lookup(anyString())).thenReturn(new Book("0140177396", "abc", "abc"));

		StockManager stockmanager = new StockManager();
		stockmanager.setWebService(webService);
		stockmanager.setDatabaseService(databaseService);

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);

		// verify(databaseService, times(1)).lookup("0140177396");
		// times(1) is the default so we don't need that parameter if we check for a 1 time call
		verify(databaseService).lookup("0140177396");
		// check that databaseService-class has been called 1 time with the method lookup and the parameter "0140177396"
		// verify(webService, times(1)).lookup("0140177396");
		// times(1) is the default so we don't need that parameter if we check for a 1 time call
		verify(webService).lookup("0140177396");
		// check that webService-Class has been called 1 time with the method lookup and the parameter "0140177396"
	}

}
