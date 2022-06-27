package lk.d24.hostel_system.dao.custom.impl;

import lk.d24.hostel_system.dao.custom.StudentDAO;
import lk.d24.hostel_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.d24.hostel_system.util.FactoryConfiguration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, id);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student find(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public List<Student> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Student> criteria = builder.createQuery(Student.class);

        // Specify criteria root
        criteria.from(Student.class);

        // Execute query
        List<Student> studentList = session.createQuery(criteria).getResultList();

        transaction.commit();
        session.close();

        return studentList;
    }
}
