package com.example.blogcode.effectivejava.item12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item12
 * fileName       : PersonTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */
class PersonTest {

    @Test
    @DisplayName("toString 메서드 테스트")
    void PersonTest() {
        Person person = Person.builder()
                .name("푸틴")
                .age(70)
                .build();

        System.out.println(person.toString());
    }

}