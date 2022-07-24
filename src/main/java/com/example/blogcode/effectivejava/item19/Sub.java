package com.example.blogcode.effectivejava.item19;

import java.time.Instant;

/**
 * packageName    : com.example.blogcode.effectivejava.item19
 * fileName       : Sub
 * author         : tkdwk567@naver.com
 * date           : 2022/07/24
 */
public final class Sub extends Super {
    // 초기화되지 않은 final 필드, 생성자에서 초기화한다.
    private final Instant instant;

    Sub() {
        this.instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
