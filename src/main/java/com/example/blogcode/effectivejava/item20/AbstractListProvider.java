package com.example.blogcode.effectivejava.item20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * packageName    : com.example.blogcode.effectivejava.item20
 * fileName       : AbstractListProvider
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */
public class AbstractListProvider {

    public static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);
        return new ArrayList<>(){
            @Override
            public Integer get(int index) {
                return a[index]; // 오토박싱
            };

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element;
                return oldVal;
            };

            @Override
            public int size() {
                return a.length;
            };
        };
    }
}
