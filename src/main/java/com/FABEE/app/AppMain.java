package com.FABEE.app;

import com.FABEE.core.Injector;
import com.FABEE.orm.OrmHandler;
import com.FABEE.app.model.User;
import com.FABEE.core.MethodInvoker;
import com.FABEE.app.DynamicExample;
import com.FABEE.core.MetadataReader;

public class AppMain {
    public static void main(String[] args) {
        // Teste: Injeção de Independencia
//        Injector.init(UserService.class, UserRepository.class);
//
//        UserService service = Injector.getBean(UserService.class);
//        service.cadastrarUsuario("Lucas");

        // Teste: ORM
//        User user = new User("001", "Lucas", "lucas@example.com");
//
//        String insert = OrmHandler.generateInsertSQL(user);
//        String select = OrmHandler.generateSelectByIdSQL(User.class, "001");
//
//        System.out.println(insert);
//        System.out.println(select);

        // Teste: Invocação Dinâmica
//        DynamicExample exemplo = new DynamicExample();
//
//        MethodInvoker.invokeMethod(exemplo, "dizerOla", "Lucas");
//
//        int resultado = (int) MethodInvoker.invokeMethod(exemplo, "somar", 5, 7);
//        System.out.println("Resultado da soma: " + resultado);

        // Teste: Leitura de Metadados
//        MetadataReader.printClassMetadata(UserService.class);
//        MetadataReader.printClassMetadata(User.class);

        // Teste: Dynamic Proxies

    }
}