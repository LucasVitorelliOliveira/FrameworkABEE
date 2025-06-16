package com.FABEE.app.controller;

import com.FABEE.annotations.*;

@Controller
public class UserController {

    @GetMapping("/users")
    public String getAllUsers() {
        return "[Lista de usuários]";
    }

    @PostMapping("/users")
    public String createUser(String nome) {
        return "Usuário criado: " + nome;
    }
}
