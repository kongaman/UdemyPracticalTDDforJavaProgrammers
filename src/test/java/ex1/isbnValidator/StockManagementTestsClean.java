package ex1.isbnValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockManagementTestsClean {

	ExternalISBNDataService webService;
	ExternalISBNDataService databaseService;
	StockManager stockmanager;

	@BeforeEach
	public void setup() {
		webService = mock(ExternalISBNDataService.class);
		databaseService = mock(ExternalISBNDataService.class);
		stockmanager = new StockManager();
		stockmanager.setWebService(webService);
		stockmanager.setDatabaseService(databaseService);
	}

	@Test
	void testCanGetACorrectLocatorCode() {

		when(databaseService.lookup("0140177396")).thenReturn(null);
		when(webService.lookup("0140177396")).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}

	@Test
	public void databaseIsUsedIfDataIsPresent() {

		when(databaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "abc", "abc"));
		
		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);

		verify(databaseService).lookup("0140177396");
		verify(webService, never()).lookup(anyString());
		
	}

	@Test
	public void webserviceIsUsedIfDataIsNotPresentInDatabase() {

		when(databaseService.lookup(anyString())).thenReturn(null);
		when(webService.lookup(anyString())).thenReturn(new Book("0140177396", "abc", "abc"));

		String isbn = "0140177396";
		String locatorCode = stockmanager.getLocatorCode(isbn);
		
		verify(databaseService).lookup("0140177396");
		verify(webService).lookup("0140177396");
	}

}
