package com.adara.pixeldataengineui.service.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface UserManagementService {
    String getByUsername(String username);

    String getAllUser();

    Integer login(UserDTO request);

    Integer createUser(UserDTO request);

    Integer deleteUser(String username);

    Integer updateUser(UserDTO request);
}
