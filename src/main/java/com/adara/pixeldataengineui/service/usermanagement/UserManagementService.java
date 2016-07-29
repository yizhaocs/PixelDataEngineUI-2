package com.adara.pixeldataengineui.service.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface UserManagementService {
    UserDTO getByUsername(String username) throws Exception;

    GenericDTOList<UserDTO> getAllUser() throws Exception;

    ResponseDTO login(UserDTO request) throws Exception;

    ResponseDTO createUser(UserDTO request) throws Exception;

    ResponseDTO deleteUser(String username) throws Exception;

    ResponseDTO updateUser(UserDTO request) throws Exception;
}
