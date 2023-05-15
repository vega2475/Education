package ReflectionAPI.First;

import ReflectionAPI.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Person();
        // В каждой из этих переменных лежит объект типа Class для класса Person.
        Class personClass1 = Person.class; // personClass - объект класса Class.
        Class personClass2 = person.getClass();
        Class personClass3 = Class.forName("ReflectionAPI.Person");

        // Получим методы класса Person
        Method[] methods = personClass1.getMethods();
        // Выведем какую-то информацию о методах класса Person.
        for (Method method : methods){
            System.out.println(method.getName() + ", " + method.getReturnType() + ", " + Arrays.toString(method.getParameterTypes()));
        }
        // Получим все поля класса Person.
        Field[] fields = personClass1.getDeclaredFields();
        for (Field field : fields){
            System.out.println(field.getName() + ", " + field.getType());
            // Если оставить код так, то в консоль ничего не выведется. Потому что у нашего класса Person только приватные поля.
            // А методы в пакете reflect учитывают инкапсуляцию. Нужно использовать getDeclaredFields(). Этот метод нарушает инкапсуляцию и покажет любые поля.
        }
        // Получим аннотации которые есть в классе Person.
    }
}

