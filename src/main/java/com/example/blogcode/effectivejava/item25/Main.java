package com.example.blogcode.effectivejava.item25;

public class Main {
    public static void main(String[] args) {
        System.out.println(Company.NAME + Product.NAME);
    }

    static class Company {
        static final String NAME = "samsung";
    }

    static class Product {
        static final String NAME = "phone";
    }
}
