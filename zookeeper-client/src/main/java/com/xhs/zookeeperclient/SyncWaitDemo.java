package com.xhs.zookeeperclient;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuhan  build  2019/1/18
 */
public class SyncWaitDemo implements Runnable{

    private Lock lock;
    private Condition condition;


    public SyncWaitDemo(Lock lock) {
        this.lock = lock;
    }

    public SyncWaitDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
//        synchronized (lock){
//            System.out.println(" wait Thread start ");
//            try {
//                lock.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(" wait Thread end ");
//        }

        lock.lock();
        System.out.println("lock await start");
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("lock await end");
        lock.unlock();
    }
}
