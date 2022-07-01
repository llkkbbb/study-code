package com.example.blogcode.project.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.project.domain
 * fileName       : MemberTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */
class MemberTest {

    @Test
    @DisplayName("멤버 객체 생성")
    void MemberTest() {
        Member member = Member.builder()
                .id(1L)
                .name("스폰지밥")
                .money(10000)
                .build();

        assertNotNull(member);

    }

}