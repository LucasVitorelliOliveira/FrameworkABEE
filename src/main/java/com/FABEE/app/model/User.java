package com.FABEE.app.model;

import com.FABEE.annotations.*;

@AutoDoc(author = "Lucas Oliveira", description = "Entidade representante de um usuário")
@Entity(tableName = "users")
public class User {

    @Id
    @NotNull(message = "ID não pode ser nulo.")
    @JsonField("user_id")
    private String id;

    @Column(name = "name")
    @NotNull(message = "Nome é obrigatório.")
    @MinLength(value = 3, message = "Nome deve ter ao menos 3 caracteres.")
    @JsonField("user_name")
    private String name;

    @Column(name = "email")
    @JsonField("user_email")
    private String email;

    public User(){}

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}