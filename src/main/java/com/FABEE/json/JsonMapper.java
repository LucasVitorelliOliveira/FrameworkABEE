package com.FABEE.json;

import com.FABEE.annotations.JsonField;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {

    public static String toJson(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            StringBuilder json = new StringBuilder("{");

            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);

                String name = field.getName();
                if (field.isAnnotationPresent(JsonField.class)) {
                    name = field.getAnnotation(JsonField.class).value();
                }

                Object value = field.get(obj);
                json.append("\"").append(name).append("\":");
                json.append("\"").append(value).append("\"");

                if (i < fields.length - 1) {
                    json.append(", ");
                }
            }

            json.append("}");
            return json.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar para JSON", e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            Map<String, String> values = new HashMap<>();
            json = json.trim().replaceAll("[{}\"]", "");
            String[] pairs = json.split(",");

            for (String pair : pairs) {
                String[] kv = pair.split(":", 2);
                if (kv.length == 2) {
                    values.put(kv[0].trim(), kv[1].trim());
                }
            }

            T instance = clazz.getDeclaredConstructor().newInstance();

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String name = field.getName();

                if (field.isAnnotationPresent(JsonField.class)) {
                    name = field.getAnnotation(JsonField.class).value();
                }

                if (values.containsKey(name)) {
                    Object value = convert(values.get(name), field.getType());
                    field.set(instance, value);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar JSON", e);
        }
    }

    private static Object convert(String value, Class<?> type) {
        return switch (type.getSimpleName()) {
            case "String" -> value;
            case "int", "Integer" -> Integer.parseInt(value);
            case "long", "Long" -> Long.parseLong(value);
            case "boolean", "Boolean" -> Boolean.parseBoolean(value);
            default -> value; // suporte simples
        };
    }
}
