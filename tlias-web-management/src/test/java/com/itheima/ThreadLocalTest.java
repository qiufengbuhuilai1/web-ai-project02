package com.itheima;

public class ThreadLocalTest {

    private  static ThreadLocal< String> local = new ThreadLocal<>();
    public static void main(String[] args) {
        local.set("tom");
        local.set("jerry");
        System.out.println(Thread.currentThread().getName()+" : "+local.get());
        local.remove();
        System.out.println(Thread.currentThread().getName()+" : "+local.get());
    }
}
