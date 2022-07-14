package com.example.blogcode.javabasic.lambda;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * packageName    : com.example.blogcode.javabasic.lambda
 * fileName       : LambdaTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Function function = list1 -> list.stream().mapToInt(Integer::intValue).sum();

        System.out.println(function.sum(list));
    }
}
