package com.zx.shiro2.beans;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String username;
    private String password;
    private String passwordSalt;
}
