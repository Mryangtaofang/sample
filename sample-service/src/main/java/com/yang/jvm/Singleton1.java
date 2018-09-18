package com.yang.jvm;

public class Singleton1 {
    private static Singleton1 singleton = new Singleton1();
    public static int value1;
    public static int value2 = 0;

    private Singleton1(){
        value1++;
        value2++;
    }

    public static Singleton1 getInstance(){
        return singleton;
    }
}
