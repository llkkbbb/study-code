package com.example.blogcode.javabasic.async;

/**
 * packageName    : com.example.blogcode.javabasic.async
 * fileName       : Main
 * author         : tkdwk567@naver.com
 * date           : 2022/07/31
 */
public class Main {

    private static TossBank tossBank = new TossBank(0);

    public static void main(String[] args) throws InterruptedException {
        Parent parent = new Parent(tossBank, 10000);
        parent.start();

        Thread.sleep(1000);

        Child child = new Child(tossBank, 4500);
        child.start();
    }
}
