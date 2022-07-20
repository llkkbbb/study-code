package com.example.blogcode.effectivejava.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.example.blogcode.effectivejava.item15
 * fileName       : FinalArraySolution
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */
public class FinalArraySolution {
    private static final int[] ARRAY = {1, 2, 3, 4, 5};
    public static final List<int []> ARRAYS  =
            Collections.unmodifiableList(Arrays.asList(ARRAY));
}
