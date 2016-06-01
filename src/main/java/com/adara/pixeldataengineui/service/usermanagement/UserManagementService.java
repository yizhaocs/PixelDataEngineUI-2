package com.adara.pixeldataengineui.service.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface UserManagementService {
    String getByUsername(String username) throws Exception;

    String getAllUser() throws Exception;

    Integer login(UserDTO request) throws Exception;

    Integer createUser(UserDTO request) throws Exception;

    Integer deleteUser(String username) throws Exception;

    Integer updateUser(UserDTO request) throws Exception;
}
