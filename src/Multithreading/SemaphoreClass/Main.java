/**
 * Этот класс нужен тогда когда у нас есть какой-то ресурс и много потоков используют этот ресурс
 * Semaphore мы используем тогда когда хотим ограничить доступ к этому ресурсу.
 * Например, сервер это ресурс и 10 потоков пишут на сервер, и семафор позволит этот ограниченный ресурс(сервер) поделить между потоками
 * Потоки могут забирать разрешение, но после того как поток сделал свою работу он отдает это разрешение.
 * -----------------------------------------------------------------------------
 *         Semaphore semaphore = new Semaphore(3); //в аргумент кол-во разрешений для потоков(сколько потоков одновременно могут писать данные на сервер)
 *         semaphore.acquire(); //когда забираем разрешение, когда начинаем работать с ресурсом, ждет если кол-во permits равно 0
 *         semaphore.release(); //отдает разрешение, тогда когда в потоке заканчиваем юзать ресурс
 *         semaphore.availablePermits(); //возвращает кол-во свободных разрешений
 * -----------------------------------------------------------------------------
 * Рассмотрим применение на примере соединение к серверу. (Типо)
 *
 */

package Multithreading.SemaphoreClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        Connection connection = Connection.getConnection();
        for(int i = 0; i < 200; i++){
            executorService.submit(() -> {
                try {
                    connection.work();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
    //Singleton - когда всего 1 объект класса
    private static class Connection{ //наш ценный ресурс который мы хотим разделить между потоками
        private final Semaphore semaphore = new Semaphore(10);
        private static final Connection connection = new Connection();
        private int connectionsCount;
        private Connection(){ //Приватный конструктор - запретили пользователям создавать объекты этого класса. В нашей программе будет 1 объект класса connection

        }
        static Connection getConnection(){
            return connection;
        }
        void work() throws InterruptedException {
            semaphore.acquire();//забрали разрешение
            try {
                doWork();
            }
            finally {
                semaphore.release(); //отдали разрешение. finally блок - что бы не случилось в методе doWork release все равно сработает.
            }
        }
        private void doWork() throws InterruptedException {//какая-то работа с соединением
            synchronized (this){
                connectionsCount++;
                System.out.println(connectionsCount);
            }
            //отослали файл на сервер
            Thread.sleep(5000);
            synchronized (this){
                connectionsCount--;
            }
        }
    }
}
