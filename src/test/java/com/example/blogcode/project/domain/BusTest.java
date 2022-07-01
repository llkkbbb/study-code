package com.example.blogcode.project.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.project.domain
 * fileName       : BusTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */
class BusTest {

    @Test
    @DisplayName("버스 객체 생성")
    void BusTest() {
        Bus bus900 = Bus.builder().id(1L)
                .name("마을버스")
                .number(900)
                .build();



    }

}