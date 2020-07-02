package com.xhs.zookeeperclient;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xuhan  build  2019/2/13
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        for(int i=0;i<10;i++){
            semaphore.acquire();
            new Thread(new MyRun(i,semaphore)).start();
        }
    }
    static class MyRun implements Runnable{
        private int i;
        private Semaphore semaphore;

        public MyRun(int i, Semaphore semaphore) {
            this.i = i;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("第"+i+"个开始了！！！！");
            try {
                int sleep =  new Random().nextInt(3);
                System.out.println("第"+i+"个sleep"+sleep);
                TimeUnit.SECONDS.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第"+i+"个结束了！！！！");
            semaphore.release();

        }
    }
}
