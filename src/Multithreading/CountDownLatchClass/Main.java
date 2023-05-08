/**
 * При создании объекта CDL мы должны в конструктор ввести кол-во итераций которые мы должны отсчитать назад
 * прежде чем защелка отопрется.
 * CountDownLatch, дословно «Запор с обратным отсчетом», – примитив синхронизации из стандартной библиотеки Java.
 * Он останавливает пришедшие потоки, пока внутренний счетчик не достигнет нуля. Чтобы поставить поток на ожидание, нужно вызвать из него метод await() .
 */

package Multithreading.CountDownLatchClass;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3); // число 3 означает то что нужно вызвать метод countdown 3 раза из любого кол-ва потоков что бы защелка открылась
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executorService.submit(new Processor(countDownLatch));
        }
        executorService.shutdown();
        countDownLatch.await(); //как только countdown доведет до 0 метод await больше не будет ждать и будет выведено сообщение
        System.out.println("Latch has been opened, main thread is proceeding");
    }
    private static class Processor implements Runnable{
        private CountDownLatch countDownLatch;
        public Processor(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            countDownLatch.countDown(); //дикрементируем count в CDL
        }
    }
}
