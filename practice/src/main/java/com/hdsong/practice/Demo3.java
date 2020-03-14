package com.hdsong.practice;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @作者 宋艾衡
 * @日期 2020-03-14
 * @时间 23:35
 * @描述
 */
public class Demo3 {


    public static void main(String[] args) {
        // 某大厂面试：去掉list集合中的重复元素
        List<String> words = Arrays.asList("a", "b", "c", "c", "d", "d", "e", "e");

        // 方案一：用过set去去重
        Set<String> set = Sets.newHashSet();
        for (String word : words) {
            set.add(word);
        }
        for (String word : set) {
            System.out.print(word);
        }

        System.out.println();

        // 方案二：通过流的方式去重
        words.stream().distinct().collect(Collectors.toList()).forEach(System.out::print);

    }
}
