package ex1.isbnValidator;

public interface ExternalISBNDataService {

	Book lookup(String isbn);

}
