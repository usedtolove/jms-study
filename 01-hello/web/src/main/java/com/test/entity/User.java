package com.test.entity;

import java.io.Serializable;

/**
 * User: 胡荆陵
 * Date: 12-12-13
 * User 实体类
 */
public class User implements Serializable{

    private String name;
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
