package com.FABEE.web;

import com.FABEE.annotations.*;

import java.lang.reflect.Method;
import java.util.*;

public class Dispatcher {

    private static final Map<String, Method> getRoutes = new HashMap<>();
    private static final Map<String, Method> postRoutes = new HashMap<>();
    private static final Map<Class<?>, Object> controllers = new HashMap<>();

    public static void init(Class<?>... classes) {
        try {
            for (Class<?> clazz : classes) {
                if (!clazz.isAnnotationPresent(Controller.class)) continue;

                Object instance = clazz.getDeclaredConstructor().newInstance();
                controllers.put(clazz, instance);

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(GetMapping.class)) {
                        String path = method.getAnnotation(GetMapping.class).value();
                        getRoutes.put(path, method);
                    } else if (method.isAnnotationPresent(PostMapping.class)) {
                        String path = method.getAnnotation(PostMapping.class).value();
                        postRoutes.put(path, method);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao iniciar Dispatcher", e);
        }
    }

    public static void simulateRequest(String method, String path, Object... params) {
        try {
            Method target = switch (method.toUpperCase()) {
                case "GET" -> getRoutes.get(path);
                case "POST" -> postRoutes.get(path);
                default -> null;
            };

            if (target == null) {
                System.out.printf("[404] Rota '%s' não encontrada.%n", path);
                return;
            }

            Object controller = controllers.get(target.getDeclaringClass());
            Object result = target.invoke(controller, params);
            if (result != null) {
                System.out.println("→ Retorno: " + result);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao simular requisição", e);
        }
    }
}
