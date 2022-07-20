package com.example.blogcode.effectivejava.item15;

/**
 * packageName    : com.example.blogcode.effectivejava.item15
 * fileName       : FinalArraySolution2
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */
public class FinalArraySolution2 {
    private static final int[] ARRAY = {1, 2, 3, 4, 5};

    public static final int[] getArray() {
        return ARRAY.clone();
    }
}
