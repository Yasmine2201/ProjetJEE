package fr.efrei.teachfinder.annotations;

import fr.efrei.teachfinder.entities.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String action();
    RoleType[] roles() default {};
}
