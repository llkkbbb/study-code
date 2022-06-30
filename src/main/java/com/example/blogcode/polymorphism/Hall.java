package com.example.blogcode.polymorphism;

/**
 * packageName    : com.study.abstract1.다형성
 * fileName       : Hall
 * author         : tkdwk567@naver.com
 * date           : 2022/06/30
 */
public class Hall extends Employee {

    @Override
    public void work() {
        System.out.println("테이블 정리");
    }

    public void wellCome() {
        System.out.println("몇분이세요?");
    }
}
