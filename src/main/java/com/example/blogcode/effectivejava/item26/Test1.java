package com.example.blogcode.effectivejava.item26;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<?> list = new ArrayList<>();

    }

    public static void add(List<?> list){
        for (Object o : list) {
            if (o instanceof String) {
                //....
            }
        }
    }
}
