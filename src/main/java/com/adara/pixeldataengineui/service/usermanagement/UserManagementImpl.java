package com.adara.pixeldataengineui.service.usermanagement;

import com.adara.pixeldataengineui.dao.usermanagement.UserManagementDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("userManagementService")
@Transactional
public class UserManagementImpl implements UserManagementService {
    @Autowired
    private UserManagementDAO mUserManagementDAO;

    public GenericDTOList<UserDTO> getAllUser() throws Exception {
        return mUserManagementDAO.getAllUser();
    }

    public UserDTO getByUsername(String username) throws Exception {
        return mUserManagementDAO.getByUsername(username);
    }

    public ResponseDTO login(UserDTO request) throws Exception {
        return mUserManagementDAO.login(request);
    }

    public ResponseDTO createUser(UserDTO request) throws Exception {
        return mUserManagementDAO.createUser(request);
    }

    public ResponseDTO deleteUser(String username) throws Exception {
        return mUserManagementDAO.deleteUser(username);
    }


    public ResponseDTO updateUser(UserDTO request) throws Exception {
        return mUserManagementDAO.updateUser(request);
    }
}
