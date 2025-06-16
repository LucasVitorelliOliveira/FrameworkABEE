package com.FABEE.app;

import com.FABEE.annotations.Repository;

@Repository
public class UserRepository {
    public void save(String name) {
        System.out.println("Salvando usuário: " + name);
    }
}