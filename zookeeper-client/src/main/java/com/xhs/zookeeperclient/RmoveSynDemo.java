package com.xhs.zookeeperclient;

import java.util.concurrent.TimeUnit;

/**
 * @author xuhan  build  2019/1/17
 */
public class RmoveSynDemo{


    public volatile static  boolean flag = true;

    public RmoveSynDemo() {
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t=new Thread(demo);
        t.start();
        TimeUnit.SECONDS.sleep(1);
        flag=false;

    }



      static  class Demo implements Runnable{
            @Override
            public void run() {
                long a=System.currentTimeMillis();
                System.out.println(a);
                int i=0;
                while (flag){
                    try {
                        i++;
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(i++);
                }
                long b=System.currentTimeMillis();
                System.out.println(b-a);

            }
        }

}
