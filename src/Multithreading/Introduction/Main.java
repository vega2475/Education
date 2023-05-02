/**
 * Многопоточность — способность платформы (например виртуальной машины,
 * операционной системы и т. д.) или приложения выполнять одновременно,
 * то есть без предписанного порядка во времени, несколько параллельных задач — потоков.
 * <p>
 * Пример многопоточности это веб-сервер, когда вы заходите на сервер создается отдельный поток.
 *
 */

package Multithreading.Introduction;

class Main {
    // 1st way for make thread - extend from Thread class
    /* 2-й способ - реализуем интерфейс Runnable и в методе run() пишем логику которая будет выполняться в отдельном потоке
    И передаем объект этого класса в качестве аргумента в конструкторе при создании нового Thread().
    */

    private static class MyThread extends Thread{
        /*После того как мы наследовались от класса Thread наш класс стал отдельным потоком
        если мы создадим в классе Main объект класса MyThread и начнем запускать код из этого класса, то он будет выполняться в отдельном потоке.
        Код в этом классе будет выполняться параллельно с потоком Main в методе main класса Main.
        */

        //что бы реализовать код котрый мы хотим выполнить в отдельном потоке нужно переопределить метод run()
        @Override
        public void run(){
            //Именно в этом методе мы пишем код который должен выполняться в отделном потоке
            try {
                message();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        private void message() throws InterruptedException {
            for(int i = 0; i < 30; i++){
                System.out.println(i + " Hello from MyThread");
                Thread.sleep(700); // статик метод класса Thread который прерывает поток на кол-во мс в аргументе
            }
        }

    }

    private static class Runner implements Runnable{ // этот способ получше

        @Override
        public void run(){
            //Именно в этом методе мы пишем код который должен выполняться в отделном потоке
            try {
                message();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        private void message() throws InterruptedException {
            for(int i = 0; i < 30; i++){
                System.out.println(i + " Hello from MyThread (Runnable)");
                Thread.sleep(700); // статик метод класса Thread который прерывает поток на кол-во мс в аргументе
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread(); // создали поток
        myThread.start(); // Запустили поток. Этот метод именно создает новый поток и запускает код в методе run(), этот метод из класса Thread.
        //запускаем второй поток
        MyThread myThread1 = new MyThread();
        myThread1.start();
        //после запуска этого кода с помощью i видно отсутствие синхронизации потоков

        //Создадим поток вторым способ через Runner
        Thread thread = new Thread(new Runner()); // это можно было сделать через анонимный класс
        thread.start();


        System.out.println("Now MainThread activate");
        // эта надпись может вывестись раньше или позже из-за отсутствия синхронизации потоков(потоки борятся за ресурсы процессора)
        //ВАЖНО!!! после того как поток main вызвал наши потоки, то он пойдет дальше по своим инструкциям и после того как поток main
        // выполнит свой код он завершиться, но не закроется полностью так как поток main породил наши потоки и он ждет их завершения (типо как рекурсия)
    }
}

