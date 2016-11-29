package thread.re_entrant_locks;

/**
 *The main program just runs the firstThread() and secondThread() methods in different threads.
 * finish() is called after both threads finish.
 */

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        System.out.println("firstThread :: lock.lock()");
        lock.lock();
        System.out.println("firstThread :: Waiting .... cond.await() run");
        cond.await();

        System.out.println("firstThread :: Woken up!");

        try {
            System.out.println("firstThread :: increment :: Start");
            increment();
            System.out.println("firstThread :: increment :: End");
        } finally {
            lock.unlock();
            System.out.println("firstThread :: lock.unlock()");
        }
    }

    public void secondThread() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("secondThread :: lock.lock()");
        lock.lock();

        System.out.println("secondThread :: Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("secondThread :: Got return key!");
        System.out.println("secondThread :: cond.signal() run");
        cond.signal();

        try {
            System.out.println("secondThread :: increment :: Start");
            increment();
            System.out.println("secondThread :: increment :: End");
        } finally {
            lock.unlock();
            System.out.println("secondThread :: lock.unlock()");
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
