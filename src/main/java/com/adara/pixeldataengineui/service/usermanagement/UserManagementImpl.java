package com.adara.pixeldataengineui.service.usermanagement;

import com.adara.pixeldataengineui.dao.usermanagement.UserManagementDAO;
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

    public String getAllUser() {
        return mUserManagementDAO.getAllUser();
    }

    public String getByUsername(String username) {
        return mUserManagementDAO.getByUsername(username);
    }

    public Integer login(UserDTO request) {
        return mUserManagementDAO.login(request);
    }

    public Integer createUser(UserDTO request) {
        return mUserManagementDAO.createUser(request);
    }

    public Integer deleteUser(String username) {
        return mUserManagementDAO.deleteUser(username);
    }


    public Integer updateUser(UserDTO request) {
        return mUserManagementDAO.updateUser(request);
    }
}
