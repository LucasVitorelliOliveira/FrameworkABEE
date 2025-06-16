package com.FABEE.app.model;

import com.FABEE.annotations.*;

@Entity(tableName = "users")
public class User {

    @Id
    @NotNull(message = "ID não pode ser nulo.")
    private String id;

    @Column(name = "name")
    @NotNull(message = "Nome é obrigatório.")
    @MinLength(value = 3, message = "Nome deve ter ao menos 3 caracteres.")
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