package com.FABEE.app;

import com.FABEE.annotations.Inject;
import com.FABEE.annotations.Service;

@Service
public class UserService implements IUserService {

    @Inject
    protected UserRepository repo;

    @Override
    public void cadastrarUsuario(String nome) {
        repo.save(nome);
    }
}
