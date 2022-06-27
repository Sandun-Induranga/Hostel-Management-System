package lk.d24.hostel_system.bo.custom;

import lk.d24.hostel_system.dto.RoomDTO;

import java.util.List;

public interface RoomBO {
    public boolean saveRoom(RoomDTO roomDTO) throws Exception;

    public boolean updateRoom(RoomDTO roomDTO) throws Exception;

    public boolean deleteRoom(String id) throws Exception;

    public List<RoomDTO> getAllRooms() throws Exception;
}
