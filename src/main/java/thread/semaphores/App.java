package thread.semaphores;

/**
 *App.java: creates 200 threads and fires them off simultaneously.
 * They all try to run the connect() method of the Connection class at the same time.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class App {

    public static void main(String[] args) throws Exception {

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i=0; i < 200; i++) {
            executor.submit(new Runnable() {
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}