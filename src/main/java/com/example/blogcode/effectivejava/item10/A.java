package com.example.blogcode.effectivejava.item10;

/**
 * packageName    : com.example.blogcode.effectivejava.item10
 * fileName       : A
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
public class A {

    // 명시적 null 검사 - 필요 없다.
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) return false;
//        /// ,,,,,
//        return false;
//    }

    // 묵시적 null 검사 - 이쪽이 더 괜찮
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof A)) return false;
        A a = (A) obj;
        /// ,,,,,
        return false;
    }
}
