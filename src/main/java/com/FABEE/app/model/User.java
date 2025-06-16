package com.FABEE.app.model;

import com.FABEE.annotations.*;

@Entity(tableName = "users")
public class User {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters/setters omitidos por simplicidade
}