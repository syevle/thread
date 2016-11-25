package thread.synchronizedtest;

/**
 * This is the third part of our advanced Java multi-threading tutorial.
 * In this tutorial we look at using the synchronized keyword to coordinate actions between threads and prevent
 * them screwing up each other's work.
 */

class Worker {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

//    public void increment() {
//        count++;
//    }

    public void run() {
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count is: " + count);
    }
}

public class App {

    public static void main(String[] args) {
        Worker pro = new Worker();
        pro.run();
    }

}
