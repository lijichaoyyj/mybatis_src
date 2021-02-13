package com.cst.test.bean;

public class Student {
    private int number;

    public int getNumber() {
        return number;
    }

     public  void setNumber(int number) {
        this.number = number;
    }

    @Override
    public Student clone() throws CloneNotSupportedException {
        Student stu=null;
        stu =(Student)super.clone();
        return stu;
    }

   protected void printStudent(){
        System.out.println("student");
    }

}
