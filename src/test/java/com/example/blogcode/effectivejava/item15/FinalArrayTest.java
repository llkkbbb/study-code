package com.example.blogcode.effectivejava.item15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Find;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item15
 * fileName       : FinalArrayTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/20
 */
class FinalArrayTest {

    @Test
    @DisplayName("public static final int[] arr test")
    void arrTest() {
        FinalArray finalArray = new FinalArray();
        int[] beforArr = finalArray.getArr();

        System.out.println(Arrays.toString(beforArr));

        finalArray.setArr(2);
        int[] afterArr = finalArray.getArr();

        System.out.println(Arrays.toString(afterArr));

        //[1, 2, 3, 4, 5]
        //[0, 1, 3, 4, 5]
    }

}