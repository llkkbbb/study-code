package com.example.blogcode.javabasic.lambda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.javabasic.lambda
 * fileName       : StringConcatImplTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
class StringConcatImplTest {

    @Test
    void StringConcatImplTest() {
        String str1 = "hello ";
        String str2 = "java";

        StringConcatImpl concat = new StringConcatImpl();
        System.out.println(concat.concat(str1, str2));


        // 람다식 사용
        StringBuffer sb = new StringBuffer();
        StringConcat concat1 = (st1, st2) -> sb.append(st1).append(str2).toString();

        StringConcat concat2 = (str11, str21) -> sb.append(str11).append(str21).toString();

        System.out.println(concat1.concat(str1, str2));
        System.out.println(concat2.concat(str1,str2));

    }

}