package com.example.blogcode.project.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    @DisplayName("버스 요금 지불 성공")
    void getOnBus_success() {
        // 버스 생성
        Bus bus900 = Bus.builder().id(1L)
                .name("마을버스")
                .number(900)
                .build();

        // 멤버 생성
        Member member = Member.builder()
                .id(1L)
                .name("스폰지밥")
                .money(10000)
                .build();

        Member busMember = member.getOnBus(bus900);

        assertThat(busMember.getMoney()).isEqualTo(member.getMoney());
    }

    @Test
    @DisplayName("버스 요금 지불 실패")
    void getOnBus_fail() {
        // 버스 생성
        Bus bus900 = Bus.builder().id(1L)
                .name("마을버스")
                .number(900)
                .build();

        // 멤버 생성
        Member member = Member.builder()
                .id(1L)
                .name("스폰지밥")
                .money(900)
                .build();

        assertThatThrownBy(() -> member.getOnBus(bus900))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔액이 부족합니다.");
    }

}