/**
 * @LINK https://cloud.mail.ru/public/ihZt/twarn4z3m
 */

package ReflectionAPI.Second;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Scanner scanner = new Scanner(System.in);
        // Название 1-го класса Название класса 2 название метода
        Class classObject1 = Class.forName(scanner.next());
        Class classObject2 = Class.forName(scanner.next());
        String methodName = scanner.next();
        /*
            newInstance(); Метод newInstance() позволяет создать объект указанного класса.
            Но что ВАЖНО отметить — такой вызов возможен только в случае, если класс имеет конструктор БЕЗ ПАРАМЕТРОВ.

            Метод getConstructor возвращает конструктор по умолчанию. А если мы хотим получить определенный конструктор,
            нам нужно передавать в этот метод типы параметров, которые будут в конструкторе. Не забывай о том, что наш приватный конструктор
            мы можем получить только с помощью метода getDeclaredConstructor.
         */
        Method m = classObject1.getMethod(methodName, classObject2);
        Object o1 = classObject1.newInstance(); // Создаем объект класса Person с пустым конструктором
        // Мы хотим получить конструктор для 2-го объекта который принимает на вход строковые значения и на этом конструкторе мы вызываем метод newInstance() который принимает на вход строковые значения и передаем само строковое значение
        Object o2 = classObject2.getConstructor(String.class).newInstance("String value"); // Создали объект с Конструктором и установили name

        m.invoke(o1, o2); // метод вызывается на объекте o1 с аргументом o2

        System.out.println(o1);
    }
}
