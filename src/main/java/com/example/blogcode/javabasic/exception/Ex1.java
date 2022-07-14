package com.example.blogcode.javabasic.exception;

import java.util.Arrays;

/**
 * packageName    : com.example.blogcode.javabasic.exception
 * fileName       : Ex1
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class Ex1 {
    public static void main(String[] args) {
        int[] arr = new int[10];

        try {
            for (int i = 0; i < arr.length + 1; i++) {
                arr[i] = i;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            String message = e.getMessage();
            System.out.println(message);
        }

        // 예외를 처리하고 정상 수행이 된다.
        // 비정상 종료가 되지 않는다.
        System.out.println(Arrays.toString(arr));
    }
}
