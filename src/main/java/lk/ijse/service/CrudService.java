package lk.ijse.service;

import lk.ijse.dto.BookDTO;

import java.util.List;



public interface CrudService <T> extends SuperService {
    boolean save(T dto);
    boolean update(T dto);
    boolean delete(Long id);
    List<T> findAll();
    List<T> search(String text);
}
