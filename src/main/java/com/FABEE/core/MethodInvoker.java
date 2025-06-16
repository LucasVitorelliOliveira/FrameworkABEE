package com.FABEE.core;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodInvoker {

    public static Object invokeMethod(Object target, String methodName, Object... args) {
        try {
            Class<?> clazz = target.getClass();
            Method method = Arrays.stream(clazz.getDeclaredMethods())
                    .filter(m -> m.getName().equals(methodName))
                    .filter(m -> m.getParameterCount() == args.length)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchMethodException("Método não encontrado: " + methodName));

            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao invocar método " + methodName, e);
        }
    }
}