package pl.bmstefanski.garbanzo.command.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandInfo {

  String name();

  String usage() default "";

  int minArguments() default 0;

  int maxArguments() default 0;

}
