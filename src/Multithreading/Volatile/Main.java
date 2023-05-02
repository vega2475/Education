/**
 * Для того что бы упорядочить работу потоков существует синхронизация потоков.
 * Ключевое слово volatile (изменчивый) - нужно тогда когда переменная делиться между двумя потоками.
 * В случае некогерентности кешей volatile исправляет эту проблему.
 * Когда один поток записывает инфу в переменную, а другой поток читает инфу из переменной.
 */

package Multithreading.Volatile;

class Main {
    private static class MyThread extends Thread{
        private volatile boolean running = true; // даем инструкцию, что эта переменная может быть изменена и не нужно ее кешировать.
        //Эта переменная будет всегда находиться в главной памяти. Теперь каждый поток будет обращаться к главной памяти, что бы взять свежее значение переменной.
        public void run(){
            while (running){
                System.out.println("Hello");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        public void shutDown(){
            this.running = false;
        }
    }
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        //пусть мы хотим из потока main выключить поток mythread
        //можно с помощью метода shutDown(), но это может не всегда работать. Это может произойти изза плохой когерентности кешей.
        //
        //что бы этот код точно работал нужно использовать volatile.
    }
}
