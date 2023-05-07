/**
 * Реализация producer-consumer собственноручно без ABQ.
 * Методы wait() и notify() определены у каждого объекта в JAVA.
 * Так как они определены у класса Object.
 * Методы многопоточности в классе Object нужны для синхронизации потоков.
 * Это происходит потому что в JAVA можно синхронизироваться на мониторе любого объекта. Материал synchronized 2.
 * Метод wait() может вызываться только внутри синохрон-блока. Вне блока он не имеет смысла.
 * Метод wait() всегда определен на объекте this. Если мы в аргумент синхрон-блока подаем другой объект, то нужно метод wait вызвать на этом объекте, а не на this.
 * Это эквивалентно this.wait().
 * Что происходит при вызове метода wait():
 * 1. Отдаем intrinsic lock. (Выходим из этого синхрон-блока)
 * 2. Мы ждем пока будет вызван notify(). Именно на объекте синхрон-блока.
 * notifyAll() Все потоки на которых был вызван метод wait() проснутся.
 * Что бы все работало важно что бы 2 потока синхронизировались на одном объекте. Что бы wait и notify были вызваны на одном и том жк объекте.
 *
 */

package Multithreading.WaitAndNotify;

import java.util.Scanner;

class Main {
    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wn = new WaitAndNotify();

        Thread thread1 = new Thread(() -> {
            try {
                wn.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                wn.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
    private static class WaitAndNotify{
        void produce() throws InterruptedException {
            synchronized (this){
                System.out.println("Producer thread started...");
                wait(); //Это эквивалентно this.wait(). Отдаем монитор и ждем вызова notify, что бы этот поток продолжил работу.
                System.out.println("Producer thread resume...");
            }
        }
        void consume() throws InterruptedException {
            Thread.sleep(2000);
            Scanner scanner = new Scanner(System.in);

            synchronized (this){
                System.out.println("Waiting for return key pressed");
                scanner.nextLine();
                notify(); //Поток на которых был вызван метод wait() проснется.
            }
        }
    }
}
