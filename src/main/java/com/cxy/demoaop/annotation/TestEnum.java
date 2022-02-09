package com.cxy.demoaop.annotation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-04 16:51
 */


public enum TestEnum {
    Dis("sdf"),
    Cis("asdgad");

    TestEnum(String msg) {
        this.msg = msg;
    }

    private String msg;
}
