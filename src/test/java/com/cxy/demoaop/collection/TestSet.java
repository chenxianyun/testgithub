package com.cxy.demoaop.collection;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-05 10:58
 */
@SpringBootTest
public class TestSet {

    @Test
    public void test1() {
        Object str = "123";
        final long[] tr = {123L};
        System.out.println(Objects.equals(tr[0], str));
        Stream.of("123", "2").parallel().anyMatch(
                x -> {
                    tr[0] = 12L;
                    return x.equals(str);
                }
        );
        System.out.println(tr[0]);
    }

    @Test
    public void test2() {
        Set<String> set = null;
        assert set != null;
        System.out.println(set.stream().map(x -> "").collect(Collectors.toList()));
    }
}
