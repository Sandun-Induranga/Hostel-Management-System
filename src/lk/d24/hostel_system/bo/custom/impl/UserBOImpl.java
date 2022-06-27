package lk.d24.hostel_system.bo.custom.impl;

import lk.d24.hostel_system.bo.SuperBO;
import lk.d24.hostel_system.bo.custom.UserBO;
import lk.d24.hostel_system.dao.DAOFactory;
import lk.d24.hostel_system.dao.custom.UserDAO;
import lk.d24.hostel_system.dto.UserDTO;
import lk.d24.hostel_system.entity.User;

public class UserBOImpl implements UserBO, SuperBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO findUser(String username) throws Exception {
        User user = userDAO.find(username);
        return new UserDTO(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        return userDAO.update(new User(userDTO.getUsername(), userDTO.getPassword()));
    }

    @Override
    public UserDTO getUser() throws Exception {
        User user = userDAO.getAll().get(0);
        return new UserDTO(user.getUsername(), user.getPassword());
    }
}
