package com.FABEE.app;

import com.FABEE.core.Injector;

public class AppMain {
    public static void main(String[] args) {
        Injector.init(UserService.class, UserRepository.class);

        UserService service = Injector.getBean(UserService.class);
        service.cadastrarUsuario("Lucas");
    }
}
