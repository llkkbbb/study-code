package com.example.blogcode.javabasic.lambda;

/**
 * packageName    : com.example.blogcode.javabasic.lambda
 * fileName       : StringConcatImpl
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class StringConcatImpl implements StringConcat {

    @Override
    public String concat(String str1, String str2) {
        StringBuffer sb = new StringBuffer(str1);
        sb.append(str2);
        return sb.toString();
    }
}
