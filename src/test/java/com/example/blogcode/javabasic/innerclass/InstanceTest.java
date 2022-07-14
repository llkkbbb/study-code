package com.example.blogcode.javabasic.innerclass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.javabasic.innerclass
 * fileName       : InstanceTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
class InstanceTest {

    @Test
    void 외부클래스_내부클래스_테스트() {
        Instance instance = new Instance();
        instance.print();
    }

}