/**
 * Thread Pool - Это удобный в использовании паттерн , позволяющий выполнять множество задач используя ресурсы множества потоков.
 * Thread Pool состоит обычно из очереди задач и нескольких потоков, которые достают задачи из очереди и выполняют их параллельно.
 *
 * ExecutorService executorService - это и будет пул из n потоков
 * Объект executorService можно представлять как работников - каждый из работников может выполнять задания, если задач больше чем n потоков,
 * то задачи в очереди ждут пока работник освободится
 * ---------------------------------------------------
 * Что такое Record в Java 16? Record — это новый тип объявления (type of declaration) для определения неизменяемых (immutable) классов данных.
 * Применение Record вместо обычного класса позволяет избежать рутинного написания методов hashCode(), equals(), toString(), геттеров и конструктора.
 * @link - https://metanit.com/java/tutorial/3.18.php
 *
 * Данную тему можно представить так: есть n работников (n потоков) и есть m коробок (m тасков) и мы им говорим:
 * Перенесите эти m коробок с пункта A в пункт B, мы сначала им всем об этом сообщаем, потом они выполняют задачи.
 * Самое главное, что они не будут хвататься за одинаковые коробки.
 */

package Multithreading.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args) throws InterruptedException {
        //Тут статиический метод newFixedThreadPool класса Executors возвращает объект класса ExecutorService, на вход кол-во потоков
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //Теперь в пуле 2 потока и мы можем этим 2-м потокам дать задание
        //Передеадим работникам 5 заданий
        for(int i = 0; i < 5; i++)
            executorService.submit(new Worker(i)); //В аргументы этому методу идет объект класса реализующий Runnable(так как в метода run() мы описываем работу для n потоков)
        //Пока что мы только отправили им задачи методом submit(), но они еще не приступили к выполнению
        //Напишем что бы приступили, после этого метода начнется выполнение заданий в потоках.
        executorService.shutdown(); //Этот метод говорит о том что мы перестаем принимать задачи на вход нам нужно их выполнять
        System.out.println("All tasks submitted");

        executorService.awaitTermination(1, TimeUnit.MINUTES); //Ожидание окончания, в арументы пишем сколько мы хотим ждать что бы потоки выполняли все задания
        //Если в течении указанного в аргументах времени потоки выполняются, то все хорошо, если нет то программа идет дальше не выполняя все задачи run()
    }

    private record Worker(int id) implements Runnable {
        @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Task " + id + " was completed");
            }
        }
}


