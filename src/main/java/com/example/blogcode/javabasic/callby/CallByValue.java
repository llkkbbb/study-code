package com.example.blogcode.javabasic.callby;

/**
 * packageName    : com.example.blogcode.javabasic.callby
 * fileName       : CallByValue
 * author         : tkdwk567@naver.com
 * date           : 2022/06/24
 */
public class CallByValue {
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println("swap 호출 전");
        System.out.println("a = " + a + " | b = " + b + "\n");


        System.out.println("swap 호출 후");
        swap(a, b);
        System.out.println("a = " + a + " | b = " + b + "\n");
    }
}
