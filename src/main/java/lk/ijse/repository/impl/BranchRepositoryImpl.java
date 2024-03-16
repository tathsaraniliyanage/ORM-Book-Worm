package lk.ijse.repository.impl;

import lk.ijse.entity.Borrowing;
import lk.ijse.entity.Branch;
import lk.ijse.repository.BranchRepository;
import org.hibernate.Session;

import java.util.List;



public class BranchRepositoryImpl implements BranchRepository {
    private Session session;

    @Override
    public Long save(Branch entity) {
        return (Long) session.save(entity);
    }

    @Override
    public void update(Branch entity) {
        session.merge(entity);
    }


    @Override
    public List<Branch> findAll() {
        return session.createQuery("SELECT b FROM Branch b").list();
    }

    @Override
    public List<Branch> search(String text) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Branch branch = isExits(id);
        System.out.println(branch.toString());
        session.delete(branch);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Branch isExits(Long id) {
        String hql = "SELECT b FROM Branch b WHERE b.id= :id ";
        return session.createQuery(hql, Branch.class)
                .setParameter("id", id).uniqueResult();
    }
}
