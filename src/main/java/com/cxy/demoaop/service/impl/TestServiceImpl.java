package com.cxy.demoaop.service.impl;

import com.cxy.demoaop.annotation.BatchRedisKey;
import com.cxy.demoaop.annotation.LockKeyType;
import com.cxy.demoaop.pojo.ParamObj;
import com.cxy.demoaop.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-04 14:44
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    @BatchRedisKey(fileName = LockKeyType.COLLECTION, value = "#paramObj.codes")
    public String test(String name, String nameCxy, ParamObj paramObj) {
        return "service->" + name;
    }


    @BatchRedisKey(fileName = LockKeyType.COLLECTION, value = "#nameCxy")
    public String test2(String name, String nameCxy, ParamObj paramObj) {
        return "service->" + name;
    }

    @BatchRedisKey(fileName = LockKeyType.COLLECTION, value = "#name")
    public String test3(String name, String nameCxy, ParamObj paramObj) {
        return "service->" + name;
    }
}
