package lk.ijse.repository.impl;

import lk.ijse.entity.Book;
import lk.ijse.entity.BorrowingDetail;
import lk.ijse.repository.BorrowingDetailRepository;
import lk.ijse.repository.RepoFactory;
import lk.ijse.repository.UserRepository;
import org.hibernate.Session;

import java.util.List;


public class BorrowingDetailRepositoryImpl implements BorrowingDetailRepository {
    private Session session;

    private UserRepository userRepository= (UserRepository) RepoFactory.getRepoFactory().getRepo(RepoFactory.RepositoryTypes.USER);

    @Override
    public Long save(BorrowingDetail entity) {
        session.clear();
        return (Long) session.save(entity);
    }

    @Override
    public void update(BorrowingDetail entity) {
        session.merge(entity);
    }

    @Override
    public List<BorrowingDetail> findAll() {
        return null;
    }

    @Override
    public List<BorrowingDetail> search(String text) {
        return null;
    }

    @Override
    public void delete(Long id) {
        BorrowingDetail borrowingDetail = new BorrowingDetail();
        borrowingDetail.setId(id);
        session.delete(borrowingDetail);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public BorrowingDetail isExits(Long id) {
        String hql = "SELECT b FROM BorrowingDetail b WHERE b.id= :id ";
        return session.createQuery(hql, BorrowingDetail.class)
                .setParameter("id", id).uniqueResult();
    }
}
