package com.FABEE.orm;

import com.FABEE.annotations.*;

import java.lang.reflect.Field;
import java.util.*;

public class OrmHandler {

    public static String generateInsertSQL(Object obj) {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Classe não anotada com @Entity");
        }

        Entity entity = clazz.getAnnotation(Entity.class);
        String table = entity.tableName().isEmpty() ? clazz.getSimpleName().toLowerCase() : entity.tableName();

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) {
                String columnName = field.isAnnotationPresent(Column.class)
                        ? field.getAnnotation(Column.class).name()
                        : field.getName();

                if (columnName.isEmpty()) columnName = field.getName();

                try {
                    Object value = field.get(obj);
                    columns.add(columnName);
                    values.add("'" + value + "'");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return String.format("INSERT INTO %s (%s) VALUES (%s);",
                table,
                String.join(", ", columns),
                String.join(", ", values)
        );
    }

    public static String generateSelectByIdSQL(Class<?> clazz, Object idValue) {
        if (!clazz.isAnnotationPresent(Entity.class)) {
            throw new IllegalArgumentException("Classe não anotada com @Entity");
        }

        Entity entity = clazz.getAnnotation(Entity.class);
        String table = entity.tableName().isEmpty() ? clazz.getSimpleName().toLowerCase() : entity.tableName();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                String column = field.getName();
                return String.format("SELECT * FROM %s WHERE %s = '%s';", table, column, idValue);
            }
        }

        throw new IllegalArgumentException("Nenhum campo anotado com @Id encontrado");
    }
}