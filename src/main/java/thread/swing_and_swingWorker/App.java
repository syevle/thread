package thread.swing_and_swingWorker;

/**
 * If you're doing Swing (GUI) programming in Java, you might want to consider using the SwingWorker class
 * for your multithreading needs. SwingWorker is a ferocious-looking but useful class that's perfect for running stuff
 * in the background while simultaneously updating progress bars and that sort of thing.
 */

import javax.swing.*;

/**
 * How to use the ReentrantLock class in Java as an alternative to synchronized code blocks.
 * ReentrantLocks let you do all the stuff that you can do with synchronized, wait and notify, plus
 * some more stuff besides that may come in handy from time to time.
 */


public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame("SwingWorker Demo");
            }
        });
    }

}