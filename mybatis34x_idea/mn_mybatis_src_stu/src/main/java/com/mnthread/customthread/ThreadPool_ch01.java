package com.mnthread.customthread;

import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPool_ch01 {

    private static  volatile boolean flag=true;

    public static void main(String[] args) {
         //用于演示效果.
       new Thread(()->{
           Scanner scanner = new Scanner(System.in);
           while (scanner.hasNext()){
               String s = scanner.nextLine();
               if("stop".equals(s)){
                   flag=false;
                   break;
               }
           }
       }).start();

       //创建线程池.
       //核心线程可以设置线程空闲存活时间(核心线程默认不超时).
        ThreadPoolExecutor  executor = new ThreadPoolExecutor(
                2,//核心线程数.
                2,//最大线程数.
                10,//线程空闲的存活时间，不包括核心线程.
                TimeUnit.SECONDS,//时间单位.
                new ArrayBlockingQueue<Runnable>(10), //阻塞线程队列。
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()); //ThreadPoolExecutor：线程饱和策略，超过线程数的线程丢弃.
        executor.allowCoreThreadTimeOut(true); //允许核心线程超时。
        executor.shutdownNow();
        //(1)AbortPolicy:抛弃超过的线程.
        //(2)CallerRunsPolicy:不丢弃任务,让调用线程池的线程帮忙执行任务.
        //(3)DiscardPolicy:抛弃最新的任务(16,17,18,19,20).
        //(4)DiscardOldestPolicy:抛弃存在时间最长的任务(3,4,5,6,7).

        //关闭策略.
        //executor.shutdown():线程池线程执行完毕关闭线程池.
        //executor.shutdownNow():立刻关闭线程池,该方法会返回未执行线程.

        for(int i=0;i<20;i++){  //循环执行20个任务.
            try{
                executor.execute(new MyRunnable("第"+(i+1)+"号任务!"));//线程池执行任务.

            }catch (Exception e){
                System.out.println("丢弃任务:"+(i+1));
                e.printStackTrace();
            }
        }
        //executor.shutdown();
    }

    static class  MyRunnable implements  Runnable{
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("MyRunnable"+Thread.currentThread().getName()+"==>"+name);
            while(flag){

            }
        }
    }


}
