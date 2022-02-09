package com.cxy.demoaop.controller;

import com.cxy.demoaop.pojo.ParamObj;
import com.cxy.demoaop.service.TestService;
import com.cxy.demoaop.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-04 14:46
 */
@RestController
public class TestController {

    @Autowired
    TestServiceImpl testService;

    @RequestMapping("/testcxy")
    public String test() {
        ParamObj paramObj = new ParamObj();
        List<String> codes = new ArrayList<>();
        codes.add("cdasf");
        codes.add("123");
        paramObj.setCodes(codes);
        String cxy = testService.test("cxy", "namecxy", paramObj);
        String cxy1 = testService.test3("cxy", "namecxy", paramObj);
        String cxy2 = testService.test2("cxy", "namecxy", paramObj);
        return "hello cxy" + "->" + cxy;
    }


}
