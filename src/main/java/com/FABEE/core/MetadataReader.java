package com.FABEE.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class MetadataReader {

    public static void printClassMetadata(Class<?> clazz) {
        System.out.println("Classe: " + clazz.getName());
        System.out.println("Superclasse: " + clazz.getSuperclass().getName());

        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            System.out.println("Interfaces implementadas:");
            for (Class<?> i : interfaces) {
                System.out.println("  - " + i.getName());
            }
        }

        System.out.println("\nCampos:");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.printf("  - %s %s (%s)%n",
                    Modifier.toString(field.getModifiers()),
                    field.getName(),
                    field.getType().getSimpleName());

            for (Annotation annotation : field.getAnnotations()) {
                System.out.println("    -> @" + annotation.annotationType().getSimpleName());
            }
        }

        System.out.println("\nMétodos:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.printf("  - %s %s(", Modifier.toString(method.getModifiers()), method.getName());
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getSimpleName());
                if (i < params.length - 1) System.out.print(", ");
            }
            System.out.println(")");
        }

        System.out.println("------------------------------------------------------\n");
    }
}