/**
 * Лямбда выражения это способ передать кусок кода в метод или в аргумент конструктора.
 * Лямбда позволяют обойтись без использования к анонимных классов.
 * Лямбда также называется - анонимной функцией - это значит что она определенв без привязки к идентефикатору, не имеет имени и т.п.
 * Любые интерфейсы с одним методом могут быть реализованны в виде лямбда функции.
 * Если мы хотим использовать переменную которая не была вызвана в лямбде и например выше, то она доожны быть final или не меняться
 * У лямбда выражений нет своей области видимости, мы можем в методе execute() и до него определить две переменные с одним именем(так как методы имеют свои скопы)
 * Облась видимости лямюды = области видимости метода/класса где лямбда была создана.
 */

package Lambda.Lambda1;

interface Executable{
    int execute(int x);
}

class Runner{
    void run(Executable e){
        int a = e.execute(7);
        System.out.println(a);
    }
}
public class Lambda {
    public static void main(String[] args) {

        //без лямбды (до JAVA 8)
        Thread threadX = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        //with lambda
        Thread thread = new Thread(() -> {

        });

        //---------------------------------------------------------------------------------//

        Runner runner = new Runner();
        runner.run(new Executable() {
            @Override
            public int execute(int x) {
                System.out.println("Hello");
                return x;
            }
        });

        final int a = 1;
        runner.run((x) -> { //тип переменной x можно не указывать так как лямбда понимает что реализует интерфейс с видит в сигнатуре тип
            System.out.println("GoodBye");
            return x + a;
        });
    }
}
