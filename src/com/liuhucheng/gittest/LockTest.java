package com.liuhucheng.gittest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        SourceClass s = new SourceClass();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                s.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                s.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                s.print15();
            }
        }, "C").start();
    }
}

class SourceClass {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    //有几个线程需要使用几个condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        try {
            lock.lock();
            //判断
            while (number != 1) {
                c1.await();
            }
            //具体逻辑
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " AA " + i);
            }

            number = 2;
            //通知
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        try {
            lock.lock();
            //判断
            while (number != 2) {
                c2.await();
            }
            //具体逻辑
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " BB " + i);
            }

            number = 3;
            //通知
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        try {
            lock.lock();
            //判断
            while (number != 3) {
                c3.await();
            }
            //具体逻辑
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + " CC " + i);
            }

            number = 1;
            //通知
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}