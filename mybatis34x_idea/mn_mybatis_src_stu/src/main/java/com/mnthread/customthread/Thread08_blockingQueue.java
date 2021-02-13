package com.mnthread.customthread;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Thread08_blockingQueue {
    //阻塞队列.


    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue(4);
        //生产者线程.
        Thread t1 = new Thread(()->{
            System.out.println("开始装入数据-----------start");
            for(int i=0;i<20;i++){
                try{
                    abq.put(i);
                    System.out.println("装入任务:"+i);
                }catch (Exception e){
                 e.printStackTrace();
                }
            }
            System.out.println("结束装入数据-----end");
        });t1.start();

        //消费者线程.

        Thread t2 = new Thread(()->{
            Scanner sca = new Scanner(System.in);
            while(sca.hasNext()){
                String next = sca.next();
                if("get".equals(next)){
                    try{
                        Integer take = abq.take();
                        System.out.println("执行任务==>"+take);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }); t2.start();
    }

}
