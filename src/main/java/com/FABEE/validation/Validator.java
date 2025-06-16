package com.FABEE.validation;

import com.FABEE.annotations.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {

    public static List<String> validar(Object obj) {
        List<String> erros = new ArrayList<>();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotNull.class)) {
                    if (value == null) {
                        NotNull notNull = field.getAnnotation(NotNull.class);
                        erros.add(notNull.message() + " Campo: " + field.getName());
                    }
                }

                if (field.isAnnotationPresent(MinLength.class)) {
                    if (value instanceof String str) {
                        int min = field.getAnnotation(MinLength.class).value();
                        if (str.length() < min) {
                            String msg = field.getAnnotation(MinLength.class).message();
                            erros.add(msg + " Campo: " + field.getName());
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return erros;
    }
}