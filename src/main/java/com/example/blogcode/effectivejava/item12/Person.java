package com.example.blogcode.effectivejava.item12;

import lombok.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item12
 * fileName       : Person
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */

@Getter
@ToString
@NoArgsConstructor
public class Person {

    private String name;
    private int age;

    @Builder
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
