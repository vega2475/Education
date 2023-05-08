/**
 * Механизм прерывания одного потока из другого потока
 * Из потока мейн прерываем тред
 * Для этого существует метод interrupt() - но этот метод не убивает поток
 * Что бы убить поток есть устаревший метод stop()
 * InterruptedException возникает тогда когда мы делаем какие-либо действия в потоке который был прерван
 */

package Multithreading.ThreadInterruption;

import java.util.Random;

class Main {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            Random random = new Random();
            for(int i = 0; i < 1_000_000_000; i++){
                //на текущем потоке вызываем метод isInterrupted(), если была дана инструкция на прерывание через метод interrupt, то вернет тру и поток прервется
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Thread was interrupted");
                    break;
                }

                Math.sin(random.nextInt());
            }
        });
        System.out.println("Starting thread");

        thread.start();

        Thread.sleep(1000);
        thread.interrupt(); // сообщение о том что мы хотим прервать поток

        thread.join();

        System.out.println("Thread has finished");
    }
}
