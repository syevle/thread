package thread.waitandnotify;

/*
* A tutorial on wait and notify in Java; low-level multithreading methods of the Object class
* that allow you to have one or more threads sleeping, only to be woken up by other threads at the right moment.
* Extremely useful for avoiding those processor-consuming polling loops.
*/

import java.util.Scanner;


class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running ....");
            System.out.println("Producer thread wait start ....");
            wait();
            System.out.println("Producer thread wait state over and Resumed for work");
            System.out.println("produce end.");
        }
    }

    public void consume() throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);

        synchronized (this) {
            System.out.println("consume::Waiting for return key.");
            scanner.nextLine();
            System.out.println("consume::Return key pressed.");
            notify();
            System.out.println("consume::consume thread notify call.");
            System.out.println("consume::consume thread Thread.sleep(5000) start.");
            Thread.sleep(5000);
            System.out.println("consume::consume thread Thread.sleep(5000) end.");
            System.out.println("consume::consume end.");

        }
    }
}


public class App {

    public static void main(String[] args) throws InterruptedException {

        final Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}