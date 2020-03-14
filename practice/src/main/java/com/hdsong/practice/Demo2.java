package com.hdsong.practice;

/**
 * @作者 宋艾衡
 * @日期 2020-03-14
 * @时间 23:29
 * @描述
 */
public class Demo2 {

    static Animal cat = () -> System.out.println("I am a cat");

    public static void zoo(Animal animal){
        animal.whoiyou();
    }

    public static void main(String[] args) {

        // 第一种方法
        zoo(() -> System.out.println("I am a dog"));

        // 第二种方法
        zoo(cat);

    }

}

// 自定义函数式接口
interface Animal{
    void whoiyou();
}
