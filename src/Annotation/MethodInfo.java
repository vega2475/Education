package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// Теперь наша аннотация будет использоваться только на методах
// Что бы использоваться 2+ типа сущностей нужно их указать в {}
// ElementType.TYPE - либо класс, либо интерфейс, либо Enum.
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // Аннотация видна в ходе выполнения программы
public @interface MethodInfo {
    String author() default "David";
    int dateOfCreation() default 2023;
    String purpose();
}
