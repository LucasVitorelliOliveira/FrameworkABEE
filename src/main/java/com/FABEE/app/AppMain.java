package com.FABEE.app;

import com.FABEE.core.Injector;
import com.FABEE.orm.OrmHandler;
import com.FABEE.app.model.User;
import com.FABEE.core.MethodInvoker;
import com.FABEE.app.DynamicExample;
import com.FABEE.core.MetadataReader;
import com.FABEE.core.LoggingProxy;
import com.FABEE.app.IUserService;
import com.FABEE.app.UserService;
import com.FABEE.validation.Validator;
import com.FABEE.json.JsonMapper;
import com.FABEE.web.Dispatcher;
import com.FABEE.app.controller.UserController;
import com.FABEE.core.SecurityProxy;
import com.FABEE.app.SecurityContext;

import java.util.List;

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
//        UserService rawService = new UserService();
//        rawService.repo = new UserRepository(); // injeção manual por enquanto
//
//        IUserService proxyService = LoggingProxy.create(IUserService.class, rawService);
//        proxyService.cadastrarUsuario("Lucas");

        // Teste: Validação Customizada
//        User u = new User(null, "Lu", "lucas@email.com");
//        List<String> erros = Validator.validar(u);
//        erros.forEach(System.out::println);

        // Teste: Serialização/Deserialização JSON
//        User user = new User("123", "Lucas", "lucas@email.com");
//
//        String json = JsonMapper.toJson(user);
//        System.out.println("JSON gerado: " + json);
//
//        User user2 = JsonMapper.fromJson(json, User.class);
//        System.out.println("Usuário reconstruído: " + user2.getName());

        // Teste: Roteamento
//        Dispatcher.init(UserController.class);
//
//        Dispatcher.simulateRequest("GET", "/users");
//        Dispatcher.simulateRequest("POST", "/users", "Lucas");
//        Dispatcher.simulateRequest("GET", "/usuarios");

        // Teste: Controle de Acesso
        UserService rawService = new UserService();
        rawService.repo = new UserRepository();

        IUserService securedService = SecurityProxy.createSecure(IUserService.class, rawService);

        // Teste com USER
        SecurityContext.setRole("USER");
        try {
            securedService.cadastrarUsuario("Lucas");
        } catch (Exception e) {
            System.out.println("🔒 " + e.getMessage());
        }

        // Teste com ADMIN
        SecurityContext.setRole("ADMIN");
        securedService.cadastrarUsuario("Lucas Admin");

    }
}