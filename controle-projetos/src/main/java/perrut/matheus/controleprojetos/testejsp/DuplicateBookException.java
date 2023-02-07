package perrut.matheus.controleprojetos.testejsp;

public class DuplicateBookException extends RuntimeException {

  private final Book book;

  public DuplicateBookException(Book book) {
    this.book = book;
  }

  public Book getBook() {
    return book;
  }
}
