package lk.ijse.repository.impl;

import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.projection.BookProjection;
import lk.ijse.repository.BookRepository;
import org.hibernate.Session;

import java.util.List;



public class BookRepositoryImpl implements BookRepository {
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Book isExits(Long id) {
        Book book = session.createQuery("SELECT b FROM Book b WHERE b.id= :id ", Book.class)
                .setParameter("id", id).uniqueResult();
        return book;

    }

    @Override
    public Long save(Book book) {
        return (Long) session.save(book);
    }

    @Override
    public void update(Book entity) {
        Branch branch = entity.getBranch();
        System.out.println(branch.toString());
        session.clear();
        session.update(entity);
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM book";
        List<Book> resultList = session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
//        List resultList = session.createNativeQuery(sql).getResultList();
        System.out.println(resultList);
        return resultList;
    }

    @Override
    public List<Book> search(String text) {
      return   session.createQuery("SELECT b FROM Book b WHERE b.genre LIKE :genre OR b.name LIKE :name OR b.author LIKE :author")
                .setParameter("genre",text+"%")
                .setParameter("name",text+"%")
                .setParameter("author",text+"%").list();
    }

    @Override
    public void delete(Long id) {
        Book book = isExits(id);
        book.setBranch(null);
        session.delete(book);
    }

    @Override
    public int count() {
        String hql = "SELECT COUNT(id) FROM Book";
        Long count = session.createQuery(hql, Long.class).getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public int availableBookCount() {
        String hql = "SELECT COUNT(id) FROM Book WHERE status = lk.ijse.entity.enumuretion.Status.AVAILABLE";
        Long count = session.createQuery(hql, Long.class).getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public int unavailableBookCount() {
        String hql = "SELECT COUNT(id) FROM Book WHERE status = lk.ijse.entity.enumuretion.Status.UNAVAILABLE";
        Long count = session.createQuery(hql, Long.class).getSingleResult();
        return Math.toIntExact(count);
    }

    @Override
    public List<BookProjection> getBooks() {
        return session.createQuery("SELECT new lk.ijse.projection.BookProjection( b.id, b.name, b.author, b.genre, b.register_date, b.status, b2.branch) FROM Book b INNER JOIN Branch b2 ON b.branch.id = b2.id").list();
    }
}
