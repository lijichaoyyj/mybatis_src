package com.cst.test;

public class MainShiTi {
    public static void main(String[] args) throws Exception {
        //getClone();
        //int value = getValue(2);
        //System.out.println("value:"+value);
        //test();
        //testStr();
        //testStr2();
        //.MainShiTi();
        //String str="hello";
        //int y=(str!=null && str.length()>0);
        NumNode numNode = new NumNode();

        new Thread(new JiNum(numNode)).start();
     new Thread(new OuNum(numNode)).start();







    }



    static class NumNode{
        int num=1;
    }

    static class JiNum implements  Runnable{
        private  NumNode  numNode;
        public  JiNum(NumNode numNode){
            this.numNode=numNode;
        }
        @Override
        public void run() {

            while(true){
                synchronized (numNode){
                if(numNode.num<100) {
                    if (numNode.num % 2 != 0) {
                        System.out.println("奇数=>" + numNode.num);
                        numNode.num++;
                        numNode.notify();
                    } else {
                        try {
                            numNode.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }else{
                    break;
                }
                }
            }

        }
    }



    static class OuNum implements  Runnable{
        private  NumNode  numNode;
        public  OuNum(NumNode numNode){
            this.numNode=numNode;
        }
        @Override
        public void run() {
            while(true){
                synchronized (numNode){
                    if(numNode.num<=100) {
                        if (numNode.num % 2== 0) {
                            System.out.println("偶数=>" + numNode.num);
                            numNode.num++;
                            numNode.notify();
                        } else {
                            try {
                                numNode.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }else{
                        break;
                    }
                }
            }

        }
    }







    public static void MainShiTi(){

        long round = Math.round(-11.5);
        System.out.println("-11.5="+Math.round(-11.5));
        System.out.println("11.5="+Math.round(11.5));
    }


    public static void testStr2() {
      String aa="hello2";
      final String bb="hello";
      String dd="hello";

      String cc=bb+2;// hello+2;  ;aa==cc;
      String ee=dd+2;//aa==ee;
      String ff="hello"+2;//aa=ff

        System.out.println("aa"+aa.hashCode());
//        System.out.println("cc"+cc.hashCode());
        System.out.println("ee"+ee.hashCode());

        System.out.println("aa==cc"+(aa==cc));
        System.out.println("aa==ee"+(aa==ee));
        System.out.println("aa==ff"+(aa==ff));



    }

    public static void testStr() {
        String a = "abc"; //创建常量池.
        String b = "abc";//常量池
        String c = new String("abc");
        String d = "ab" + "c";//String d="abc";//常量池

        System.out.println("a == b;"+(a == b)); //true
        System.out.println("a == c;" +(a == c)); //false

        System.out.println("a == d;" + (a == d)); //false
        System.out.println("b == c;" + (b == c)); //false
        System.out.println("b == d;" + (b == d)); //false
        System.out.println("c == d;" + (c == d)); //false

    }


    public static void test(){
        String a="a";
        String b="b";
        String c="c";
        c=a+""+b+""+c;
        int ia = a.hashCode();
        int ib= b.hashCode();
        int ic=c.hashCode();

        System.out.print(ia+";"+ib+";"+ic);
    }

    private static void getClone() {
        //
//        Student stu1  = (Student) Class.forName("com.cst.test.bean.Student").newInstance();
//        stu1.setNumber(123456);
//        System.out.println("stu1:"+stu1.getNumber()); //int默认值为0
//
//        Student stu2= stu1.clone();
//        stu2.setNumber(21);
//        System.out.println("stu2:"+stu2.getNumber());
//        System.out.println("stu21:"+stu1.getNumber());
        int i=0;
        System.out.println(i++);
        System.out.println(++i);
    }

    public static int getValue(int i) {

        int result = 0;

        switch (i) {

            case 1:

                result = result + i;
                System.out.println("1");

            case 2:

                result = result + i * 2;

            case 3:

                result = result + i * 3;
            default:
                System.out.println("defa");

        }

        return result;

    }

}
