/**
 * Synchronized блок.
 * В этом блоке пишем код который должен быть синхронизированным.
 * В аргумент synchronized блока передаем объект на мониторе которого хотим синхронизироваться.
 * Ускорим работу программы синхронизируясь на разных объектах.
 * Выполнение метода work() в одном потоке занимает 2165мс. С учетом sleep на 1к элементов каждому листу.
 * Выполнения метода work() в двух потоках занимает 2164мс. С учетом sleep на <2к элементов(из-за RC). Заполняются не фул. На 1990.
 * С synchronized в методах, листы заполняются фул, но время выполнения возрастает в 2 раза.
 * Долгое выполнение происходит из-за того что мы синхронизируемся на одном мониторе объекта исполнителя Main().
 * А так как брат ресурсы монитора одного объекта может только один поток из-за этого смысл многопоточности теряется и не имеет смысла.
 * Мы хотим теперь сделать так что бы первый поток выполнял один метод, второй поток другой метод.
 * После распределения на два монитора время работы ускорилось. Avg 3250.
 */

package Multithreading.Synchronized.Part2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Main {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Worker worker = new Worker();
        worker.main();

    }
    private static class Worker{
        //Из строки 11 реализовываем: создаем два любых объекта.
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        Random random = new Random();
        private List<Integer> list1 = new ArrayList<>();
        private List<Integer> list2 = new ArrayList<>();
        void addToListOne() throws InterruptedException {
            synchronized (lock1){ //первый поток забрал монитор lock1, lock2 свободен
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(1);
                    list1.add(random.nextInt(100));
                }
            }
        }
        void addToListTwo() throws InterruptedException {
            synchronized (lock2){ //второй поток забрал монитор lock2
                for (int i = 0; i < 1000; i++) {
                    Thread.sleep(1);
                    list2.add(random.nextInt(100));
                }
            }
        }
        void work() throws InterruptedException {
            addToListOne();
            addToListTwo();
        }
        void main() throws InterruptedException {
            long before = System.currentTimeMillis();

            Thread thread1 = new Thread(() -> {
                try {
                    work();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            Thread thread2 = new Thread(() -> {
                try {
                    work();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            long after = System.currentTimeMillis();

            System.out.println(after - before);
            System.out.println("List1 " + list1.size());
            System.out.println("List2 " + list2.size());
        }
    }
}

