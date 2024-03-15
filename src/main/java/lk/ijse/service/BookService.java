package lk.ijse.service;

import lk.ijse.dto.BookDTO;
import lk.ijse.projection.BookProjection;

import java.util.List;



public interface BookService extends CrudService<BookDTO> {
    int count();

    BookDTO getBook(Long id);

    List<BookProjection> getBooks();
}
