package lk.d24.hostel_system.dao.custom.impl;

import lk.d24.hostel_system.dao.custom.QueryDAO;
import lk.d24.hostel_system.entity.CustomEntity;
import lk.d24.hostel_system.entity.SuperEntity;
import lk.d24.hostel_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getAllReservations() throws Exception {
        return getData("SELECT r.resId, r.student_studentId, s.name, r.room_roomId, Room.type, r.date, r.keyMoney, r.keyMoneyStatus FROM Reservation r JOIN Room JOIN Student s ON r.room_roomId = Room.roomId && r.student_studentId=s.studentId");
    }

    @Override
    public List<CustomEntity> getPaidReservations() throws Exception {
        return getData("SELECT r.resId, r.student_studentId, s.name, r.room_roomId, Room.type, r.date, r.keyMoney, r.keyMoneyStatus FROM Reservation r JOIN Room JOIN Student s ON r.room_roomId = Room.roomId && r.student_studentId=s.studentId WHERE r.keyMoneyStatus='Paid'");
    }

    @Override
    public List<CustomEntity> getPendingReservations() throws Exception {
        return getData("SELECT r.resId, r.student_studentId, s.name, r.room_roomId, Room.type, r.date, r.keyMoney, r.keyMoneyStatus FROM Reservation r JOIN Room JOIN Student s ON r.room_roomId = Room.roomId && r.student_studentId=s.studentId WHERE r.keyMoneyStatus='Pending'");
    }

    private List<CustomEntity> getData(String query){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Object[]> list = session.createSQLQuery(query).list();
        List<CustomEntity> customEntityList = new ArrayList<>();
        for (Object[] entity : list) {
            customEntityList.add(new CustomEntity((String) entity[0],(String) entity[1],(String) entity[2],(String) entity[3],(String) entity[4], LocalDate.parse(entity[5].toString()),(BigDecimal) entity[6],(String) entity[7]));
        }

        transaction.commit();
        session.close();
        return customEntityList;
    }
}
