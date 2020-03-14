package com.hdsong.practice;

import java.util.Arrays;

/**
 * @作者 宋艾衡
 * @日期 2020-03-14
 * @时间 23:14
 * @描述
 */
public class Demo0 {

    public static void main(String[] args) {
        // 将int数组转换成integer数组

        int[] nums = {4,2,8,6,5,3,7,1};

        Integer[] integers = Arrays.stream(nums).sorted().boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));


    }

}
