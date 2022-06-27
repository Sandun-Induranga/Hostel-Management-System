package lk.d24.hostel_system.dao.custom.impl;

import lk.d24.hostel_system.dao.custom.RoomDAO;
import lk.d24.hostel_system.entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.d24.hostel_system.util.FactoryConfiguration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws Exception {
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

        Room room = session.load(Room.class, id);

        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room find(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.find(Room.class, id);

        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public List<Room> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Room> criteria = builder.createQuery(Room.class);

        criteria.from(Room.class);

        List<Room> roomList = session.createQuery(criteria).getResultList();

        transaction.commit();
        session.close();

        return roomList;
    }
}
