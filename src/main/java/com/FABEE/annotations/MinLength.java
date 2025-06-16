package com.FABEE.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinLength {
    int value();
    String message() default "Tamanho mínimo não respeitado.";
}