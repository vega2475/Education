/**
 * Эти классы позволяют возвращать значения из потоков и выбрасывать исключения из потоков
 * Мы не можем вернуть значение из нашего потока так как run() void.
 * Что бы можно было нужно использовать не Runnable, а Callable.
 * Что бы получить доступ к возвращаемому значению из потока нужно использовать интерфейс Future
 * Так же с помощью этих классов можно ловить исключения в потоке main которые возникают в производном потоке
 */

package Multithreading.CallableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

class Main {
    public static void main(String[] args)  {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() { //объект future параметризуем тем же типом, что и в Callable.
            @Override
            public Integer call() throws Exception {
                System.out.println("Starting");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Finished");
                Random random = new Random();
                int randomValue = random.nextInt(10);
                if(randomValue < 5)throw new Exception("Smth happened");
                return randomValue;
            }
        });
        executorService.shutdown();


        int result = 0; //get дожидается окончания выполнения потока
        try {
            result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) { //в этой ветке вы ловим исключение выполнения из производного потока, во время выполнения потока
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage()); //получаем текст исключения
        }
    }
}
