package com.example.blogcode.javabasic.exception;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : com.example.blogcode.javabasic.exception
 * fileName       : Ex2
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class Ex2 {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream("src/main/resources/test.txt");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }


    }
}
