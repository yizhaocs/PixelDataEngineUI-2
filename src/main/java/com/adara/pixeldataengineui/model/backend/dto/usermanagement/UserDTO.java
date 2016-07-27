package com.adara.pixeldataengineui.model.backend.dto.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class UserDTO extends BaseDTO implements Serializable {
    private String username;
    private String password;

    public UserDTO(){

    }

    public UserDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDTO [username=" + username + ", password=" + password + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());

        return result;
    }
}
