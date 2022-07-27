package com.example.blogcode.effectivejava.item24;

/**
 * packageName    : com.example.blogcode.effectivejava.item24
 * fileName       : OuterTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */
public class OuterTest {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.inner inner = outer.new inner();

        // or

        Outer.inner inner1 = new Outer().new inner();
    }
}
