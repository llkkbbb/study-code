package com.example.blogcode.javabasic.innerclass;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.javabasic.innerclass
 * fileName       : OuterTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
class OuterTest {

    @Test
    void OuterTest() {
        Outer outer = new Outer();

        Runnable runnable = outer.runnable();
        runnable.run();

        outer.runnable.run();
    }

}