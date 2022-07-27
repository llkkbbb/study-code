package com.example.blogcode.effectivejava.item23;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item23
 * fileName       : SquareTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */
class SquareTest {

    @Test
    void SquareTest() {
        Square square = new Square(2);
        System.out.println(square.area());

        System.out.println(square.getLength());
        System.out.println(square.getWidth());
        System.out.println(square.getSide());
    }

}