package lk.ijse.repository.impl;

import lk.ijse.entity.User;
import lk.ijse.entity.enumuretion.TypeUser;
import lk.ijse.repository.UserRepository;
import org.hibernate.Session;

import java.util.List;



public class UserRepositoryImpl implements UserRepository {
    private Session session;

    @Override
    public Long save(User entity) {
        return (Long) session.save(entity);
    }

    @Override
    public void update(User entity) {
        session.merge(entity);

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> search(String text) {
        return null;
    }

    @Override
    public void delete(Long id) {
        User user = new User();
        user.setId(id);
        session.delete(user);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public User isExits(Long id) {
        String hql = "SELECT u FROM User u WHERE u.id= :id ";
        return session.createQuery(hql, User.class)
                .setParameter("id", id).uniqueResult();
    }

    @Override
    public User auth(String password, String username) {
        return session.createQuery("SELECT u FROM User u WHERE u.username=:un AND u.password=:p",User.class)
                .setParameter("un",username)
                .setParameter("p",password)
                .uniqueResult();
    }
}
