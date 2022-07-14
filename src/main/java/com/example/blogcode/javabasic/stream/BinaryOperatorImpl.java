package com.example.blogcode.javabasic.stream;

import java.util.function.BinaryOperator;

/**
 * packageName    : com.example.blogcode.javabasic.stream
 * fileName       : BinaryOperatorImpl
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class BinaryOperatorImpl implements BinaryOperator<Integer> {

    @Override
    public Integer apply(Integer integer, Integer integer2) {
        if (integer > integer2) return integer2;
        else return integer;
    }

}
