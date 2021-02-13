package com.mnthread.mainclazz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClazz {

    public static void main(String[] args) {
        pool1();
    }

    public static void  pool1(){

        ExecutorService  es = Executors.newCachedThreadPool();

        //for (int i=0;i<10;i++){
            es.execute(()->{
                  for(int j=0;j<10;j++){

                      try {
                          Thread.sleep(100);
                      }catch (Exception e){
                          e.printStackTrace();
                      }
                      System.out.println("ThreadName:"+Thread.currentThread().getName()+"j");

                  }
            });
            es.shutdown();

       // }



    }


}
