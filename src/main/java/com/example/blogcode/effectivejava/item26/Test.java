package com.example.blogcode.effectivejava.item26;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hello");

//        add(list);
    }

    private static void add(List<Object> list) {
        list.forEach(System.out::println);
    }
}
