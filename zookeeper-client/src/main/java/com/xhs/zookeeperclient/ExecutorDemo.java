package com.xhs.zookeeperclient;

import ch.qos.logback.core.util.TimeUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xuhan  build  2019/1/16
 */
public class ExecutorDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ArrayList<Future> futures = new ArrayList<Future>();

            futures.add(executorService.submit(new myCallbale()));
            futures.add(executorService.submit(new myCallbale()));
            futures.add(executorService.submit(new myCallbale()));
           executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("runnable");
                }
            });

        for(int i=0;i<futures.size();i++){
            System.out.println("future get的值"+futures.get(i).get());
        }
    }

    public synchronized void test(){
        int a =0 ;
    }

    public  void test1(){
        synchronized(this){

        }
        int a =0 ;
    }

    private static class myCallbale implements Callable{

        @Override
        public Object call() throws Exception {
            int time = new Random().nextInt(1000);
            TimeUnit.MILLISECONDS.sleep(time);
            System.out.println(Thread.currentThread().getName()+"线程睡眠时间："+time+"毫秒");
            return time;
        }
    }
}
