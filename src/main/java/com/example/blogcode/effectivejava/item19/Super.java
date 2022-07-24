package com.example.blogcode.effectivejava.item19;

/**
 * packageName    : com.example.blogcode.effectivejava.item19
 * fileName       : Super
 * author         : tkdwk567@naver.com
 * date           : 2022/07/24
 */
public class Super {

    // 생성자가 재정의 가능 메서드를 호출한다.
    public Super() {
        overrideMe();
    }

    public void overrideMe() {
        System.out.println("재정의 가능 메서드");
    }
}
