package com.example.blogcode.javabasic.exception;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * packageName    : com.example.blogcode.javabasic.exception
 * fileName       : Ex3
 * author         : tkdwk567@naver.com
 * date           : 2022/07/15
 */
public class Ex3 {
    public static void main(String[] args) {

        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/test.txt")) {

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
}
