package com.xhs.zookeeperclient;

import java.util.concurrent.TimeUnit;

/**
 * @author xuhan  build  2019/1/22
 */
public class SyncStaticDemo {


    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() ->{
            SyncStaticDemo.printA();
        });
        t.start();
//        t.join();
        new Thread(() ->{
            SyncStaticDemo.printB();
        }).start();
    }

    public synchronized static void printA(){
        System.out.println("printA start ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("printA end ");
    }
    public synchronized static void printB(){
        System.out.println("printB start");
        System.out.println("printB end");
    }
}
