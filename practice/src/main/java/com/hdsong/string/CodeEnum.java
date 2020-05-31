package com.hdsong.string;

import lombok.Getter;

/**
 * @作者 宋寒冬
 * @日期 2020-05-05
 * @时间 14:01
 * @描述
 */

@Getter
public enum  CodeEnum {
    error("0000","失败{0}{1}");


    private String code;
    private String message;

    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
