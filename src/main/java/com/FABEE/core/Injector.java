package com.FABEE.core;

import com.FABEE.annotations.Inject;
import com.FABEE.annotations.Service;
import com.FABEE.annotations.Repository;

import java.lang.reflect.Field;
import java.util.*;

public class Injector {
    private static final Map<Class<?>, Object> container = new HashMap<>();

    public static void init(Class<?>... classes) {
        try {
            // 1. Instancia e registra serviços e repositórios
            for (Class<?> clazz : classes) {
                if (clazz.isAnnotationPresent(Service.class) || clazz.isAnnotationPresent(Repository.class)) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    container.put(clazz, instance);
                }
            }

            // 2. Realiza a injeção nos campos @Inject
            for (Object obj : container.values()) {
                for (Field field : obj.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Inject.class)) {
                        Class<?> fieldType = field.getType();
                        Object dependency = container.get(fieldType);
                        if (dependency != null) {
                            field.setAccessible(true);
                            field.set(obj, dependency);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha na injeção de dependência", e);
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return clazz.cast(container.get(clazz));
    }
}