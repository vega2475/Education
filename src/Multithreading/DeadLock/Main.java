/**
 * Симуляция банковских операций
 * Deadlock или дедлок или взаимная блокировка — это ошибка, которая происходит когда нити имеют циклическую зависимость от пары синхронизированных объектов.
 * Представьте, что одна нить входит в монитор объекта x, а другая — объекта y. Если нить в объекте x пытается вызвать любой синхронизированный метод объекта y,
 * а объект y в то же самое время пытается вызвать любой синхронизированный метод объекта x, то нити застрянут в процессе ожидания.
 * DeadLock в нашем случае происходит из-за синхронизации в разном порядке, если поток1 лочится на лок1, а поток 2 на лок2, то дальше они будут бесконечно друг друга ждать.
 * Способы избежать DeadLock:
 * 1. Забирать локи в одном порядке
 * 2. Использование метода tryLock() и логику в методе takeLocks
 */

package Multithreading.DeadLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Main {
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread thread1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        runner.finished();
    }

    private static class Runner {
        private final Account account1 = new Account();
        private final Account account2 = new Account();
        private final Lock lock1 = new ReentrantLock();
        private final Lock lock2 = new ReentrantLock();

        private void takeLock(Lock lock1, Lock lock2) throws InterruptedException {
            boolean firstLockTaken = false;
            boolean secondLockTaken = false;

            // Если лок занят - тру, если свободен фолс
            while (true) { //выходим из цикла только если два потока залочены (два лока залочены)
                try {
                    firstLockTaken = lock1.tryLock();
                    secondLockTaken = lock2.tryLock();
                } finally {
                    if (firstLockTaken && secondLockTaken) {
                        return;
                    }
                    if (firstLockTaken) {
                        lock1.unlock();
                    }
                    if (secondLockTaken) {
                        lock2.unlock();
                    }
                }
                Thread.sleep(1);
            }
        }

        void firstThread() throws InterruptedException {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                takeLock(lock1, lock2);
                try {
                    Account.transfer(account1, account2, random.nextInt(100));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        }

        void secondThread() throws InterruptedException {
            Random random = new Random();
            for (int i = 0; i < 10000; i++) {
                takeLock(lock2, lock1);
                try {
                    Account.transfer(account2, account1, random.nextInt(100));
                } finally {
                    lock1.unlock();
                    lock2.unlock();
                }
            }
        }

        void finished() {
            System.out.println(account1.getBalance());
            System.out.println(account2.getBalance());
            System.out.println("Total balance = " + (account1.getBalance() + account2.getBalance()) + "€");
        }

        private static class Account {
            private int balance = 10000;

            void deposit(int amount) {
                balance += amount;
            }

            void withdraw(int amount) {
                balance -= amount;
            }

            int getBalance() {
                return this.balance;
            }

            static void transfer(Account account1, Account account2, int amount) { //Перевод денег с акка1 на акк2
                account1.withdraw(amount);
                account2.deposit(amount);
            }
        }
    }
}
