package com.FABEE.core;

import java.lang.reflect.*;

public class LoggingProxy {

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> interfaceClass, T realObject) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                (proxy, method, args) -> {
                    System.out.printf("[LOG] Método '%s' chamado com argumentos: %s%n",
                            method.getName(),
                            args != null ? java.util.Arrays.toString(args) : "[]");

                    return method.invoke(realObject, args);
                }
        );
    }
}
