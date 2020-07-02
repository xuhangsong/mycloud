package com.xhs.zookeeperclient;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuhan  build  2019/1/18
 */
public class Demo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        SyncWaitDemo waitDemo = new SyncWaitDemo(lock,condition);
        SyncNotifyDemo notifyDemo = new SyncNotifyDemo(lock,condition);
        new Thread(waitDemo).start();
        new Thread(notifyDemo).start();

    }
}
