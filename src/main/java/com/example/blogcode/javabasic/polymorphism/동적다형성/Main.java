package com.example.blogcode.javabasic.polymorphism.동적다형성;

/**
 * packageName    : com.study.abstract1.다형성.동적다형성
 * fileName       : Main
 * author         : tkdwk567@naver.com
 * date           : 2022/06/30
 */
public class Main {
    public static void main(String[] args) {
        JindoDog jindoDog = new JindoDog();
        Dog dog = new Dog();

        if (jindoDog instanceof Dog) {
            dog = (Dog) jindoDog;
        }

    }
}
