/**
 * RL - нужен для того что бы синхронизировать потоки.
 * С помощью этого класса мы делаем все что и с synchronized.
 * Но синхронизация через RL имеет свои плюсы. Об этом можно узнать в пакете с DeadLock.
 *
 */

package Multithreading.ReentrantLockClass;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Main {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(() -> {
            task.firstThread();
        });
        Thread thread2 = new Thread(() -> {
            task.secondThread();
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        task.showCounter();
    }
    private static class Task{
        private Lock lock = new ReentrantLock();
        private int counter;

        private void increment(){
            for(int i = 0; i < 10000; i++){
                counter++;
            }
        }

        void firstThread(){
            lock.lock(); //при вызове lock тут, выполнение залочится и при вызове lock в секонд тред lock вернет то что залочено, при unlock - анлок.
            increment();
            lock.unlock();
        }

        void secondThread(){
            lock.lock();
            increment();
            lock.unlock();
        }

        void showCounter(){
            System.out.println(counter);
        }
    }
}
