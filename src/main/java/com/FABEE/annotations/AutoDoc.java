package com.FABEE.annotations;

import java.lang.annotation.*;

//@Retention(RetentionPolicy.SOURCE)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoDoc {
    String author();
    String description();
}
