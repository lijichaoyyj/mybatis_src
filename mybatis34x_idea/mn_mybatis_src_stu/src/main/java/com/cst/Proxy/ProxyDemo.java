package com.cst.Proxy;


import java.lang.reflect.Method;

interface  Test{
    public  void  say() throws NoSuchMethodException;

}

interface  InvocationHandler{
  Object   invoke(Object obj, Method method, Object ... arg);
}


public class ProxyDemo {

    public static void main(String[] args) {


    }


    public  Test  createProxyInstance(Class<?> clazz,InvocationHandler handler){

        return  new Test(){

            @Override
            public void say(){
                try {
                    Method method = clazz.getMethod("say");
                    handler.invoke(this,method);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        };


    }




}
