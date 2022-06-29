package lk.d24.hostel_system.bo.custom.impl;

import lk.d24.hostel_system.bo.SuperBO;
import lk.d24.hostel_system.bo.custom.ReserveRoomBO;
import lk.d24.hostel_system.dao.DAOFactory;
import lk.d24.hostel_system.dao.custom.QueryDAO;
import lk.d24.hostel_system.dao.custom.ReservationDAO;
import lk.d24.hostel_system.dao.custom.RoomDAO;
import lk.d24.hostel_system.dao.custom.StudentDAO;
import lk.d24.hostel_system.dto.CustomDTO;
import lk.d24.hostel_system.dto.ReservationDTO;
import lk.d24.hostel_system.dto.RoomDTO;
import lk.d24.hostel_system.dto.StudentDTO;
import lk.d24.hostel_system.entity.CustomEntity;
import lk.d24.hostel_system.entity.Reservation;
import lk.d24.hostel_system.entity.Room;
import lk.d24.hostel_system.entity.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReserveRoomBOImpl implements ReserveRoomBO, SuperBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY_DAO);

    @Override
    public List<StudentDTO> loadAllStudents() throws Exception {
        List<Student> all = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student :
                all) {
            studentDTOS.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getPhoneNumber(), student.getDob(), student.getGender()));
        }
        return studentDTOS;
    }

    @Override
    public List<RoomDTO> loadAllRooms() throws Exception {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room :
                all) {
            roomDTOS.add(new RoomDTO(room.getRoomId(), room.getType(), room.getKeyMoney(), room.getQty()));
        }
        return roomDTOS;
    }

    @Override
    public StudentDTO findStudentById(String id) throws Exception {
        Student student = studentDAO.find(id);
        return new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getPhoneNumber(), student.getDob(), student.getGender());
    }

    @Override
    public RoomDTO findRoomById(String id) throws Exception {
        Room room = roomDAO.find(id);
        return new RoomDTO(room.getRoomId(), room.getType(), room.getKeyMoney(), room.getQty());
    }

    @Override
    public boolean reserveRoom(String reservationId, String studentId, String roomId, String paymentStatus) throws Exception {
        Student student = studentDAO.find(studentId);
        Room room = roomDAO.find(roomId);
        room.setQty(room.getQty() - 1);
        roomDAO.update(room);
        return reservationDAO.save(new Reservation(reservationId, LocalDate.now(), room.getKeyMoney(), paymentStatus, student, room));
    }

    @Override
    public boolean updateReservation(String reservationId, String studentId, String roomId, String paymentStatus) throws Exception {
        Student student = studentDAO.find(studentId);
        Room room = roomDAO.find(roomId);
        return reservationDAO.update(new Reservation(reservationId, LocalDate.now(), room.getKeyMoney(), paymentStatus, student, room));
    }

    @Override
    public String generateNewReservationId() throws Exception {
        return reservationDAO.generateNewId();
    }

    @Override
    public List<CustomDTO> getAllReservations() throws Exception {
        return getData(queryDAO.getAllReservations());
    }

    @Override
    public List<CustomDTO> getPaidReservations() throws Exception {
        return getData(queryDAO.getPaidReservations());
    }

    @Override
    public List<CustomDTO> getPendingReservations() throws Exception {
        return getData(queryDAO.getPendingReservations());
    }

    private List<CustomDTO> getData(List<CustomEntity> allReservations){
        List<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomEntity entity:
                allReservations) {
            customDTOS.add(new CustomDTO(entity.getResId(),entity.getStudentId(),entity.getName(),entity.getRoomId(),entity.getType(),entity.getDate(),entity.getKey_money(),entity.getKeyMoneyStatus()));
        }
        return customDTOS;
    }
}
