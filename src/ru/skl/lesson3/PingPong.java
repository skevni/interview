package ru.skl.lesson3;


public class PingPong {
    private final Object monitor;
    private boolean lock;

    public PingPong() {
        this.monitor = new Object();
        this.lock = true;
    }

    public void printPing(String threadName) {
        synchronized (monitor) {
            try {
                while (!lock) {
                    monitor.wait();
                }
                System.out.println(threadName + " Ping");
                lock = false;
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void printPong(String threadName) {
        synchronized (monitor) {
            try {
                while (lock) {
                    monitor.wait();
                }
                System.out.println(threadName + " Pong");
                lock = true;
                monitor.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
