package thread.low_level_producer_consumer;



import java.util.LinkedList;
import java.util.Random;

public class Processor {

    private LinkedList<Integer> list = new LinkedList<Integer>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {

        int value = 0;

        while (true) {

            synchronized (lock) {

                while(list.size() == LIMIT) {
                    System.out.println("produce :: lock.wait() call start");
                    lock.wait();
                    System.out.println("produce :: lock.wait() call end");
                }
                value = value + 1;
                list.add(value);
                System.out.println("producer add value in List "+value+" List size is: " + list.size());
                System.out.println("producer :: lock.notify() before");
                lock.notify();
                System.out.println("producer :: lock.notify() after");
            }

        }
    }

    public void consume() throws InterruptedException {

        Random random = new Random();

        while (true) {

            synchronized (lock) {

                while(list.size() == 0) {
                    System.out.println("consume :: lock.wait() call start");
                    lock.wait();
                    System.out.println("consume :: lock.wait() call end");
                }

                System.out.println("consume :: List size is: " + list.size());
                int value = list.removeFirst();
                System.out.print("; value is: " + value);
                System.out.println("consumer :: lock.notify() before");
                lock.notify();
                System.out.println("consumer :: lock.notify() after");
            }
            System.out.println("consumer :: Thread.sleep(random.nextInt(1000)) start");
            Thread.sleep(random.nextInt(1000));
            System.out.println("consumer :: Thread.sleep(random.nextInt(1000)) end");
        }
    }
}
