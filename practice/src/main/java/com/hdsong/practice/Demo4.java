package com.hdsong.practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @作者 宋寒冬
 * @日期 2020-03-14
 * @时间 23:50
 * @描述
 */
public class Demo4 {


    public static void main(String[] args) {
        // 使用正则表达式 获取网页中图片的url地址
        String html = "<img src=\"http://123123123\">";

        // 匹配规则
        String reg = "<img src=\"([\\w/:.-]+)\">";

        // 编译匹配规则
        Pattern pattern = Pattern.compile(reg);

        // 匹配字符串中的结果
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }

}
