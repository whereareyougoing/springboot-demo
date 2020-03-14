package com.hdsong.practice;


import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

/**
 * @作者 宋艾衡
 * @日期 2020-03-14
 * @时间 23:00
 * @描述
 */
public class Demo1 {

    static List<Dog> dogs = Lists.newArrayList();

    @Data
    static class Dog{
        private String name;
        private Double price;

        public Dog(String name, Double price) {
            this.name = name;
            this.price = price;
        }
    }

    static {
        dogs.add(new Dog("boa",100.00));
        dogs.add(new Dog("bob",100.00));
        dogs.add(new Dog("boc",100.01));
        dogs.add(new Dog("bod",100.02));
        dogs.add(new Dog("boe",100.02));
        dogs.add(new Dog("bof",100.03));
        dogs.add(new Dog("bog",100.04));
        dogs.add(new Dog("boh",100.05));
        dogs.add(new Dog("boi",100.05));
        dogs.add(new Dog("boj",100.06));
    }

    public static void sort(){
        dogs.stream().sorted(Comparator.comparing(Dog::getPrice).reversed().thenComparing(Dog::getName))
        .forEach(System.out::println);
    }

    public static void main(String[] args) {
        // 将集合中的元素按照价格降序排序，相同的按照名字升序排序

        dogs.stream().sorted(Comparator.comparing(Dog::getPrice)
                .reversed().thenComparing(Dog::getName)).forEach(System.out::println);


    }


}
