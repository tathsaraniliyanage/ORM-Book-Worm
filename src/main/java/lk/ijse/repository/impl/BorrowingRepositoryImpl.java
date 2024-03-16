package lk.ijse.repository.impl;

import lk.ijse.entity.Borrowing;
import lk.ijse.entity.BorrowingDetail;
import lk.ijse.projection.NotReturnUsers;
import lk.ijse.repository.BorrowingRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;



public class BorrowingRepositoryImpl implements BorrowingRepository {
    private Session session;

    @Override
    public Long save(Borrowing entity) {
        return (Long) session.save(entity);
    }

    @Override
    public void update(Borrowing entity) {
        session.merge(entity);
    }

    @Override
    public List<Borrowing> findAll() {
        return null;
    }

    @Override
    public List<Borrowing> search(String text) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Borrowing borrowing = new Borrowing();
        borrowing.setId(id);
        session.delete(borrowing);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Borrowing isExits(Long id) {
        String hql = "SELECT b FROM Borrowing b WHERE b.id= :id ";
        return session.createQuery(hql, Borrowing.class)
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public List<NotReturnUsers> getNotReturnUsers() {
        Query query = session.createQuery("select new lk.ijse.projection.NotReturnUsers( b2.id ,b.id , u.username , b2.name , b.borrowing_date , b.received_date,u.Tel) FROM BorrowingDetail bd INNER JOIN Borrowing b ON bd.borrowing.id = b.id INNER JOIN User u ON b.user.id = u.id INNER JOIN Book b2 ON bd.book.id = b2.id WHERE bd.status='PENDING'");
        return query.list();
    }

    @Override
    public List<NotReturnUsers> getNotReturnUsersSearch(String searchText) {
        Query query = session.createQuery("select new lk.ijse.projection.NotReturnUsers( b2.id ,b.id , u.username , b2.name , b.borrowing_date , b.received_date,u.Tel) FROM BorrowingDetail bd INNER JOIN Borrowing b ON bd.borrowing.id = b.id INNER JOIN User u ON b.user.id = u.id INNER JOIN Book b2 ON bd.book.id = b2.id WHERE bd.status='PENDING' AND u.username LIKE :username OR u.Tel LIKE :tel OR b2.name LIKE :name");
        return query
                .setParameter("username",searchText+"%")
                .setParameter("tel",searchText+"%")
                .setParameter("name",searchText+"%")
                .list();
    }
}
