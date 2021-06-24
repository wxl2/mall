package com.weekpro.mall;

import lombok.Data;

/**
 * @author wxl
 * @date 2021/6/17 下午6:17
 * @packageName com.weekpro.mall
 * TODO
 */
@Data
public class User {
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return password;
    }

    public void setPasswd(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public User(String username, String passwd, String role) {
        this.username = username;
        this.password = passwd;
        this.role = role;
    }
    public User(){}
    public void setRole(String role) {
        this.role = role;
    }
}
