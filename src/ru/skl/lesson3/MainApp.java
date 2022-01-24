package ru.skl.lesson3;

public class MainApp {
    public static void main(String[] args) {
        // 1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
        task1();
        // 2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.
        task2();

    }

    private static void task1() {
        PingPong outPing = new PingPong();
        for (int i = 0; i < 100; i++) {
            Thread tr = new Thread(() -> outPing.printPing(Thread.currentThread().getName() + ":"));
            Thread tr2 = new Thread(() -> outPing.printPong(Thread.currentThread().getName() + ":"));
            tr.start();
            tr2.start();
            try {
                tr.join();
                tr2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void task2() {
        MyLock myLock = new MyLock();

        for (int i = 0; i < 100; i++) {
            Thread threadIncrease = new Thread(() -> myLock.increase(5, Thread.currentThread().getName()));
            Thread threadDecrease = new Thread(() -> myLock.decrease(Thread.currentThread().getName()));
            threadIncrease.start();
            threadDecrease.start();

            try {
                threadIncrease.join();
                threadDecrease.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total counter: " + myLock.getCounter());

    }
}
