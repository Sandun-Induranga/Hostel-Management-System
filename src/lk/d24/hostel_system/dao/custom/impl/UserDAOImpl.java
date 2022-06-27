package lk.d24.hostel_system.dao.custom.impl;

import lk.d24.hostel_system.dao.custom.UserDAO;
import lk.d24.hostel_system.entity.Student;
import lk.d24.hostel_system.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.d24.hostel_system.util.FactoryConfiguration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User find(String username) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, username);

        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);

        List<User> userList = session.createQuery(criteria).getResultList();

        transaction.commit();
        session.close();
        return userList;
    }
}
