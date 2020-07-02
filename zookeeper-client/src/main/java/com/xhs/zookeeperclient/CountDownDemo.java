package com.xhs.zookeeperclient;

import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhan  build  2019/1/18
 */
public class CountDownDemo {


    public static void main(String[] args) throws InterruptedException {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for(int i=0;i<count;i++){
            new Thread(() ->{
                try {
                    TimeUnit.SECONDS.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println("当前线程是："+Thread.currentThread().getName());
            }).start();
        }
        System.out.println("主线程阻塞");
        countDownLatch.await();
        System.out.println("主线程结束");
    }
}
