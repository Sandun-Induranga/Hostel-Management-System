package lk.d24.hostel_system.bo.custom;

import lk.d24.hostel_system.dto.CustomDTO;
import lk.d24.hostel_system.dto.ReservationDTO;
import lk.d24.hostel_system.dto.RoomDTO;
import lk.d24.hostel_system.dto.StudentDTO;

import java.util.List;

public interface ReserveRoomBO {
    public List<StudentDTO> loadAllStudents() throws Exception;

    public List<RoomDTO> loadAllRooms() throws Exception;

    public StudentDTO findStudentById(String id) throws Exception;

    public RoomDTO findRoomById(String id) throws Exception;

    public boolean reserveRoom(String reservationId, String studentId, String roomId, String paymentStatus) throws Exception;

    public boolean updateReservation(String reservationId, String studentId, String roomId, String paymentStatus) throws Exception;

    public String generateNewReservationId() throws Exception;

    public List<CustomDTO> getAllReservations() throws Exception;

    public List<CustomDTO> getPaidReservations() throws Exception;

    public List<CustomDTO> getPendingReservations() throws Exception;
}
