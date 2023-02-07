package perrut.matheus.controleprojetos.testejsp;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

  @Override
  public Collection<Book> getBooks() {
    var book = new Book("teste","teste","teste");
    return Arrays.asList(book);
  }

  @Override
  public Book addBook(Book book) {
    // throw new DuplicateBookException(book);
    return book;
  }
}
