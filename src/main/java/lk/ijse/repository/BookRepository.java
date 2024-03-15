package lk.ijse.repository;

import lk.ijse.entity.Book;
import lk.ijse.projection.BookProjection;

import java.util.List;



public interface BookRepository extends CrudRepository<Book>, Custom<Book> {
    int count();

    List<BookProjection> getBooks();
}
