package com.adara.pixeldataengineui.dao.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface UserManagementDAO {
    String getAllUser();

    String getByUsername(String username);

    Integer login(UserDTO request);

    Integer createUser(UserDTO request);

    Integer deleteUser(String username);

    Integer updateUser(UserDTO request);
}
