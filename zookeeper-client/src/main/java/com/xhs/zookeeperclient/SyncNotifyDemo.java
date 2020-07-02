package com.xhs.zookeeperclient;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xuhan  build  2019/1/18
 */
public class SyncNotifyDemo implements Runnable{

    private Lock lock;
    private Condition condition;

    public SyncNotifyDemo(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public SyncNotifyDemo(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        synchronized (lock){
//            System.out.println(" notify Thread start ");
//                lock.notify();
//            System.out.println(" notify Thread end ");
//        }
        lock.lock();
        System.out.println("lock signal start");
        condition.signal();
        System.out.println("lock signal end");
        lock.unlock();
    }
}
