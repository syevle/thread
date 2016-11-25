package thread.createtread;

class RunnerTwo extends Thread {

    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            System.out.println("Hello: " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}


public class ThreadWithClass {


    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnerTwo());
        thread1.start();
    }

}
