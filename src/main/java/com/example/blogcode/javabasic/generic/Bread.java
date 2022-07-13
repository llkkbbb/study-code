package com.example.blogcode.javabasic.generic;

/**
 * packageName    : com.example.blogcode.javabasic.generic
 * fileName       : Bread
 * author         : tkdwk567@naver.com
 * date           : 2022/07/13
 */

public class Bread {

    private String breadName;

    public Bread() {}

    public Bread(Material material) {
        this.breadName = material.getName();
    }
}
