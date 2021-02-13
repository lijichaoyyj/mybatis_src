package com.mnthread.customthread.mnlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    //可从入锁,默认是非公平锁.
    final static ReentrantLock reentrantLock=new ReentrantLock(true);

    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                testSyn();
            }
        };
        t1.setName("t1");

        Thread t2 = new Thread(){
            @Override
            public void run() {
                testSyn();
            }
        };
        t2.setName("t2");
        t1.start();
        t2.start();


    }

    private static void testSyn(){
         //加可重入锁.
        // reentrant:/riː'entrənt/:可重入.

        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
           Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }


    }


}
