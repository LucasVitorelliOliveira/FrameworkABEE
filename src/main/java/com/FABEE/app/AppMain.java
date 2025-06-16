package com.FABEE.app;

import com.FABEE.orm.OrmHandler;
import com.FABEE.app.model.User;

public class AppMain {
    public static void main(String[] args) {
        // Injeção já testada antes
        // Agora ORM:
        User user = new User("001", "Lucas", "lucas@example.com");

        String insert = OrmHandler.generateInsertSQL(user);
        String select = OrmHandler.generateSelectByIdSQL(User.class, "001");

        System.out.println(insert);
        System.out.println(select);
    }
}