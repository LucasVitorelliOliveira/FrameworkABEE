package com.FABEE.core;

import com.FABEE.annotations.AdminOnly;
import com.FABEE.app.SecurityContext;

import java.lang.reflect.*;

public class SecurityProxy {

    @SuppressWarnings("unchecked")
    public static <T> T createSecure(Class<T> interfaceClass, T realObject) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                (proxy, method, args) -> {
                    try {
                        Method realMethod = realObject.getClass().getMethod(method.getName(), method.getParameterTypes());

                        if (realMethod.isAnnotationPresent(AdminOnly.class)) {
                            if (!"ADMIN".equals(SecurityContext.getRole())) {
                                throw new SecurityException("Acesso negado: requer perfil ADMIN.");
                            }
                        }

                        return realMethod.invoke(realObject, args);
                    } catch (InvocationTargetException ex) {
                        // Desencapsular exceção real
                        throw ex.getCause();
                    }
                }
        );
    }
}