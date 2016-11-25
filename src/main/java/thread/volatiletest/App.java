package thread.volatiletest;

/**
 * The second part of the advanced Java multi-threading tutorial.
 * In this tutorial we look at using the volatile keyword to communicate between threads using flags.
 * We also look at why the potential uses of volatile are somewhat limited.
 */

import java.util.Scanner;

class Processor extends Thread {

    private volatile boolean running = true;

    @Override
    public void run() {

        while(running) {
            System.out.println("Running");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {

    public static void main(String[] args) {
        Processor pro = new Processor();
        pro.start();

        // Wait for the enter key
        new Scanner(System.in).nextLine();

        pro.shutdown();
    }

}