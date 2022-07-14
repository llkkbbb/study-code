package com.example.blogcode.javabasic.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * packageName    : com.example.blogcode.javabasic.stream
 * fileName       : IntArray
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class IntArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        int sum = Arrays.stream(arr).sum();
        int count = (int) Arrays.stream(arr).count();
        int maxValue = Arrays.stream(arr).max().orElseThrow(); // 반환타입 Optional
        int minValue = Arrays.stream(arr).min().orElseThrow();

//        Arrays.stream(arr).forEach(System.out::println);

//        Arrays.stream(arr).filter(value -> value > 2).forEach(System.out::println);
        int[] array = Arrays.stream(arr).sorted().toArray();


        Integer[] integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        for (Integer integer : integers) {
            System.out.println(integer);
        }

        Arrays.stream(arr).distinct().toArray();
    }
}
