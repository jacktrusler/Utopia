package com.project.week2.Utopia_airlines.JDBC_Utopia.Tables;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class User {
    private int role_id;
    private String given_name;
    private String family_name;
    private String username;
    private String email;
    private String password;
    private String phone;

    public List<String> userFields = List.of("role_id", "given_name", "family_name", "username", "email", "password", "phone");

    public User(int role_id, String given_name, String family_name, String username, String email, String password, String phone) {
        this.role_id = role_id;
        this.given_name = given_name;
        this.family_name = family_name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}
