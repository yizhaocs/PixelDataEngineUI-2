package com.adara.pixeldataengineui.model.backend.dto.usermanagement;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class UserDTO {
    private String username;
    private String password;

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
}
