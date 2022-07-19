package com.example.blogcode.effectivejava.item14;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * packageName    : com.example.blogcode.effectivejava.item14
 * fileName       : WordList
 * author         : tkdwk567@naver.com
 * date           : 2022/07/19
 */
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();

        String str = "ggg";
        String str1 = "aa";
        String str2 = "g";

        Collections.addAll(s, str, str1, str2);
        System.out.println(s);
    }
}
