package com.xhs.zookeeperclient;

import java.lang.reflect.Field;

/**
 * @author xuhan  build  2019/1/7
 */
public class IntegerDemo {

//    private static Integer a= 1;
//    private static Integer b= 2;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Integer c = 1;
//        Integer d = new Integer(1);
        Integer a=1,b=2;
//        Integer a=1,b=Integer.valueOf("1");
        System.out.println("a="+a+",b="+b);
//        new IntegerDemo().convert1(a,b);
        new IntegerDemo().convert2(a,b);
        System.out.println("a="+a+",b="+b);
//
//        System.out.println("c="+c);
//        System.out.println(a==b);
    }

    public static void convert1(Integer a , Integer b){
        Integer c = a;
        a = b;
        b=c;
    }
    public  void convert2(Integer a , Integer b) throws NoSuchFieldException, IllegalAccessException {
        Integer c1 = new Integer(a);
        Field field = Integer.class.getDeclaredField("value");
        field.setAccessible(true);
        field.set(a,b);
        field.set(b,c1);
        Integer c = 1;
        System.out.println(c);
        System.out.println("convert a="+a +" b="+b);
    }
    public static void convert3(Integer a , Integer b){
        Integer c = a;
        a = b;
        b = c;
    }
}
