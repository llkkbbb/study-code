package com.example.blogcode.effectivejava.item15;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.example.blogcode.effectivejava.item15
 * fileName       : FinalArray
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */

@NoArgsConstructor
public class FinalArray {
    public static final int[] ARRAY = {1, 2, 3, 4, 5};

    public int[] getArr() {
        return ARRAY;
    }

    public void setArr(int num) {
        for (int i = 0; i < num; i++) {
            ARRAY[i] = i;
        }
    }
}
