package lk.d24.hostel_system.dao.custom.impl;

import lk.d24.hostel_system.dao.custom.ReservationDAO;
import lk.d24.hostel_system.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import lk.d24.hostel_system.util.FactoryConfiguration;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean save(Reservation entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws Exception {
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
    public Reservation find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Reservation> getAll() throws Exception {
        return null;
    }

    @Override
    public String generateNewId() throws Exception {
        String newId = "RES001";
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<String> list = session.createSQLQuery("select resId from Reservation order by resId desc limit 1").list();

        if (!list.isEmpty()) {
            String id = list.get(0);
            int newCustomerId = Integer.parseInt(id.replace("RES", "")) + 1;
            newId = String.format("RES%03d", newCustomerId);
        }

        transaction.commit();
        session.close();
        return newId;
    }
}
