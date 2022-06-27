package lk.d24.hostel_system.dao.custom;

import lk.d24.hostel_system.dao.CrudDAO;
import lk.d24.hostel_system.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    public String generateNewId() throws Exception;
}
