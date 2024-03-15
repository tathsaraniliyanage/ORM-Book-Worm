package lk.ijse.repository;

import lk.ijse.dto.BookDTO;
import lk.ijse.service.SuperService;

import java.util.List;



public interface CrudRepository<T> extends SuperRepo {
    Long save(T entity);
    void update(T entity);
    List<T> findAll();
    List<T> search(String text);
    void delete(Long id);
}
