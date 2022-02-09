package com.cxy.demoaop.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-04 14:56
 */
@Setter
@Getter
public class ParamObj {
    private String name;
    private Integer age;
    private List<String> codes;
}
