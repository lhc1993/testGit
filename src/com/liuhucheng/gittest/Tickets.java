package com.liuhucheng.gittest;

public class Tickets {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println();
        },"A").start();
        new Thread(()->{
            System.out.println();
        },"A").start();
        new Thread(()->{
            System.out.println();
        },"A").start();
    }
}

class TicketsClass {

    private int number = 0;

    public synchronized void increament() throws Exception{
        if(number !=0 ){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+" "+number);
        this.notify();
    }

    public synchronized void decreament()throws Exception{
        if(number != 1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+" "+number);
    }
}
