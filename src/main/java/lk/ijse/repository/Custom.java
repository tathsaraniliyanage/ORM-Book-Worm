package lk.ijse.repository;

import org.hibernate.Session;



public interface Custom<T> extends SuperRepo{
    void setSession(Session session);
    T isExits(Long id);
}
