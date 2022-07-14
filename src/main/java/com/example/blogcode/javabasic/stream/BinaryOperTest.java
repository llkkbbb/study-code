package com.example.blogcode.javabasic.stream;

import java.util.Arrays;

/**
 * packageName    : com.example.blogcode.javabasic.stream
 * fileName       : BinaryOperTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class BinaryOperTest {
    public static void main(String[] args) {
        BinaryOperatorImpl binaryOperator = new BinaryOperatorImpl();

        int[] arr = {};

        int minValue = Arrays.stream(arr).reduce(10, (left, right) -> {
            if (left > right) return left;
            else return right;
        });



    }
}
