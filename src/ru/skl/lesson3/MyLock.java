package ru.skl.lesson3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    private final Lock lock;
    private int counter;

    public MyLock() {
        this(0);
    }

    public MyLock(int counter) {
        this.lock = new ReentrantLock();
        this.counter = counter;
    }

    public void increase(String thread) {
        boolean isLockAcquired = false;
        try {
            isLockAcquired = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isLockAcquired) {
            try {
                this.counter++;
                System.out.println(thread + " thread increased by one");
            } finally {
                lock.unlock();
            }
        }
    }

    public void increase(int value, String thread) {
        boolean isLockAcquired = false;
        try {
            isLockAcquired = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isLockAcquired) {
            try {
                this.counter += value;
                System.out.println(thread + " thread increased by " + value);
            } finally {
                lock.unlock();
            }
        }
    }

    public void decrease(String thread) {
        boolean isLockAcquired = false;
        try {
            isLockAcquired = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isLockAcquired) {
            try {
                this.counter--;
                System.out.println(thread + " thread decreased by one");
            } finally {
                lock.unlock();
            }
        }
    }

    public void decrease(int value, String thread) {
        boolean isLockAcquired = false;
        try {
            isLockAcquired = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isLockAcquired) {
            try {
                this.counter -= value;
                System.out.println(thread + " thread decreased by " + value);
            } finally {
                lock.unlock();
            }
        }
    }

    public int getCounter() {
        return counter;
    }
}
