package lk.d24.hostel_system.dao.custom;

import lk.d24.hostel_system.dao.SuperDAO;
import lk.d24.hostel_system.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<CustomEntity> getAllReservations() throws Exception;

    public List<CustomEntity> getPaidReservations() throws Exception;

    public List<CustomEntity> getPendingReservations() throws Exception;
}
