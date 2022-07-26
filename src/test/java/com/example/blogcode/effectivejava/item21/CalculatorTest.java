package com.example.blogcode.effectivejava.item21;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item21
 * fileName       : CalculatorTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */
class CalculatorTest {

    @Test
    void CalculatorTest() {
        Calculator calculator = new Calculator();
        int add = calculator.add(1, 2);
        int minus = calculator.minus(1, 2);

        System.out.println(add);
        System.out.println(minus);
    }

}