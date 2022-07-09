package com.example.blogcode.javabasic.polymorphism;

/**
 * packageName    : com.study.abstract1.다형성
 * fileName       : Kitchen
 * author         : tkdwk567@naver.com
 * date           : 2022/06/30
 */
public class Kitchen extends Employee {

    @Override
    public void work() {
        System.out.println("음식 만들기");
    }
}
