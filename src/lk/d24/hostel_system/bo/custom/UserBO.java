package lk.d24.hostel_system.bo.custom;

import lk.d24.hostel_system.dto.UserDTO;

public interface UserBO {
    public UserDTO findUser(String username) throws Exception;

    public boolean updateUser(UserDTO userDTO) throws Exception;

    public UserDTO getUser() throws Exception;
}
