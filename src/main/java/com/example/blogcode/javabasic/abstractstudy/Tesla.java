package com.example.blogcode.javabasic.abstractstudy;

/**
 * packageName    : com.example.blogcode.javabasic.abstractstudy
 * fileName       : Tesla
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */
public class Tesla extends Car {

    // fields....

    @Override
    public void modeChange() {
        System.out.println("테슬라 자율주행 로직");
    }
}
