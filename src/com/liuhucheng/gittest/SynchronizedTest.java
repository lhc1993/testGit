package com.liuhucheng.gittest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class SynchronizedTest {

    public static void main(String[] args) {

        Phone phone = new Phone("wo");
        Phone phone2 = new Phone("ni");

        List<String> list = new ArrayList<>();

        new Vector<String>();

        new HashMap<String,String>();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        //保证A优先于B先启动
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                phone.sendSMS();
                System.out.println(new Phone("你好" ).toString());

                //phone.sayHello();
                //phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}

class Phone {
    private int number = 10;
    private String name;
    public Phone(String name){
        this.name = name;
    }

    public static synchronized void sendEmail() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"*********sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName()+"*********sendSMS");
    }

    public void sayHello() {
        System.out.println("*********sayHello");
    }
}
