/**
 * Реализация паттерна Producer-Consumer собственноручно
 */

package Multithreading.OwnProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread thread1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
    private static class ProducerConsumer{
        private Queue<Integer> queue = new LinkedList<>();
        private final int LIMIT = 10; //максимальный размер очереди
        private final Object lock = new Object(); //объект на котором будет происходить синхронизация
        void produce() throws InterruptedException {
            int value = 0;
            while(true) {
                synchronized (lock) {
                    while (queue.size() == LIMIT) {
                        lock.wait();
                    }
                    queue.offer(value++);
                    System.out.println("New element = " + value);
                    lock.notify();//что бы wait из метода consume продолжил работу
                }
            }
        }
        void consume() throws InterruptedException {
            while (true){
                synchronized (lock){
                    while (queue.size() == 0){
                        lock.wait();
                    }
                    int value = queue.poll();
                    System.out.println(value);
                    System.out.println("Queue size = " + queue.size());
                    lock.notify();//что бы wait из метода produce продолжил работу
                }
                Thread.sleep(1000);
            }
        }
    }
}
