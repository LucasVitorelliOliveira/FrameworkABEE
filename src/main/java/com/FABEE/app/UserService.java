package com.FABEE.app;

import com.FABEE.annotations.Inject;
import com.FABEE.annotations.Service;

@Service
public class UserService {

    @Inject
    private UserRepository repo;

    public void cadastrarUsuario(String nome) {
        repo.save(nome);
    }
}
