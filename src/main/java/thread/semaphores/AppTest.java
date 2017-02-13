package thread.semaphores;

/**
 *App.java: creates 200 threads and fires them off simultaneously.
 * They all try to run the connect() method of the Connection class at the same time.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class AppTest {

    public static void main(String[] args) throws Exception {
//        Semaphore sem = new Semaphore(0);
        Semaphore sem = new Semaphore(1);

        sem.acquire();

        sem.release();

        System.out.println("Avilable permits : "+sem.availablePermits());
    }

}