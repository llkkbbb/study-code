package com.example.blogcode.javabasic.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * packageName    : com.example.blogcode.javabasic.io
 * fileName       : HelperFactory
 * author         : tkdwk567@naver.com
 * date           : 2022/06/26
 */

public class HelperFactory {
    public static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

}
