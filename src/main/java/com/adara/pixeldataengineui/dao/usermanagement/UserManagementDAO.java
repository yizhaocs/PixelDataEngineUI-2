package com.adara.pixeldataengineui.dao.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface UserManagementDAO {
    String getAllUser() throws Exception;

    String getByUsername(String username) throws Exception;

    ResponseDTO login(UserDTO request) throws Exception;

    Integer createUser(UserDTO request) throws Exception;

    Integer deleteUser(String username) throws Exception;

    Integer updateUser(UserDTO request) throws Exception;
}
