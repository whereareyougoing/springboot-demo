package com.hdsong.string;

import java.text.MessageFormat;

/**
 * @作者 宋寒冬
 * @日期 2020-05-05
 * @时间 08:28
 * @描述
 */
public class StringFormat {


    /**
     * printf
     */
    public static void print1(){
        String hello = "hello";
        System.out.printf("%s world", hello);
        System.out.println();
    }

    /**
     *   %1$s
     */
    public static void print2(){
        String world = "world";
        String flag = "!!";
        System.out.println(String.format("hello %1$s %2$s",world,flag));

    }

    /**
     *   {}  占位符
     */

    public static void print3(){
        String world = "world";
        String flag = "!!!!!";
        System.out.println(MessageFormat.format("hello {0}  {1}", world,flag));
    }


    public static void print4(){
        String world = "world";

        String flag = "!!!";

        System.out.println(MessageFormat.format("hello "+CodeEnum.error.getMessage(),world,flag));

    }

    public static void main(String[] args) {
        print1();
        print2();
        print3();
        print4();
    }



}
