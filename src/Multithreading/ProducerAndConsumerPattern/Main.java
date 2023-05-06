/**
 * Паттерн producer - consumer (производитель потребитель).
 * Часто бывает когда 1+ потоков что-то производят, а другие потребляют.
 * Пример: есть суперкомпьютер и множество математиков, и каждый математик дает сложные мат задачи компьютеру
 * Тоесть 1 и более производителей дают задачи потребителю и потом 1 и более потоков решают примеры для каждого производителя.
 * Тоесть множество математиков - производители, множество потоков - потребители.
 * И каждый поток как решит задачу вернет ответ тому пользователю кто задал задачу.
 * Мы реализуем этот паттерн с помощью ArrayBlockingQueue.
 * Все классы из java.util.concurrent синхронизованны по умолчанию.
 * Тут будет реализоция с помощью утилит JAVA.
 * Producer - тот кто кладет в пул таск, Consumer - тот кто достает из пула и выполняет.
 */

package Multithreading.ProducerAndConsumerPattern;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Эта очередь настроена на работу с множеством потоков
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);//Интерфейс BlockingQueue класс который реализует ArrayBlockingQueue. Как с List и ArrayList. В конструктор размер очереди.
        Thread thread1 = new Thread(() -> { //Поток производитель
            try {
                produce(blockingQueue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> { //Поток потребитель
            try {
                consume(blockingQueue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join(); //Ждем в потоке main когда выполнится этот поток.
        thread2.join(); //Ждем в потоке main когда выполнится этот поток.
    }
    private static void produce(BlockingQueue<Integer> blockingQueue) throws InterruptedException {
        Random random = new Random();
        while (true){
            blockingQueue.put(random.nextInt(100));//Поместили число в очередь, метод put ждет если очередь заполнена. Этот метод потокобезопасен.
        }
    }
    private static void consume(BlockingQueue<Integer> blockingQueue) throws InterruptedException {
        Random random = new Random();
        while (true){
            Thread.sleep(100);
            if(random.nextInt(10) == 5) {
                System.out.println(blockingQueue.take()); //Метод take ждет если в queue нет элементов. Этот метод потокобезопасен.
                System.out.println("Queue size = " + blockingQueue.size());
            }
        }
    }
}
