package lk.d24.hostel_system.bo.custom.impl;

import lk.d24.hostel_system.bo.SuperBO;
import lk.d24.hostel_system.bo.custom.RoomBO;
import lk.d24.hostel_system.dao.DAOFactory;
import lk.d24.hostel_system.dao.custom.RoomDAO;
import lk.d24.hostel_system.dto.RoomDTO;
import lk.d24.hostel_system.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO, SuperBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.save(new Room(roomDTO.getRoomId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(roomDTO.getRoomId(), roomDTO.getType(), roomDTO.getKeyMoney(), roomDTO.getQty()));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws Exception {
        List<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room :
                all) {
            roomDTOS.add(new RoomDTO(room.getRoomId(), room.getType(), room.getKeyMoney(), room.getQty()));
        }
        return roomDTOS;
    }
}
