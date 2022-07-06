package com.example.blogcode.project.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.atomicIntegerFieldUpdater;
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
        // given
        int money = 10000; // 멤버 생성

        // when
        Member member = createMember(money);

        // then
        assertNotNull(member);
        assertThat(member.getMoney()).isEqualTo(money);
    }

    @Test
    @DisplayName("버스 승차 테스트 -> 잔액, 승차 시간, 승객 수")
    void getOnBus_success() {
        // given
        Bus bus = createBus(); // 버스 생성
        Member member = createMember(10000); // 멤버 생성

        // when
        Member busMember = member.getOnBus(bus); // 버스 승차

        // then
        assertThat(busMember.getMoney()).isEqualTo(member.getMoney()); // 잔액 검증
        assertThat(busMember.getGetOnTheBusDateTime()).isBefore(LocalDateTime.now()); // 승차 시간 검증
        assertThat(bus.getPassengers()).isEqualTo(1); // 버스 승객수 검증
    }

    @Test
    @DisplayName("잔액 부족 테스츠 -> IllegalArgumentException 에러 검증")
    void getOnBus_fail() {
        // given
        Bus bus900 = createBus(); // 버스 생성
        Member member = createMember(900); // 멤버 생성

        // when && then
        assertThatThrownBy(() -> member.getOnBus(bus900))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잔액이 부족합니다."); // 잔액 부족 에러 검증
    }

    @Test
    @DisplayName("버스 하차 -> 잔액, 승객 수, 하차 시간")
    void getOffBus() {
        // given
        Member member = createMember(10000); // 멤버 생성
        Bus bus = createBus(); // 버스 생성

        // when
        member.getOnBus(bus); // 승차
        member.getOffBus(bus); // 하차

        // then
        assertThat(member.getMoney()).isEqualTo(9000); // 잔액 검증
        assertThat(bus.getPassengers()).isEqualTo(0); // 숭객수 검증
        assertThat(member.getGetOffTheBusDateTime()).isBefore(LocalDateTime.now()); // 하차 시간 검증
    }

    @Test
    @DisplayName("버스 환승 테스트 -> 하차 후 10초 안에 다시 승차하면 환승된다. 환승 시에 버스 비용 지불은 안된다.")
    void checkTransfer() {
        // given
        int money = 10000;
        Member member = createMember(money); // 멤버 생성
        Bus bus = createBus(); // 버스 생성
        member.getOnBus(bus); // 승차
        member.getOffBus(bus); // 하차

        // when
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            member.getOnBus(bus);
        });
        thread.run(); // 환승

        // then
        assertThat(bus.getPassengers()).isEqualTo(1); // 하차 후 다시 환승 한거니 승객수는 1명이다.
        assertThat(member.getMoney()).isEqualTo(money - bus.getPayment()); // 환승은 비용 지불을 하지 않는다. 처음 승차 한 비용만 지불된다.
        assertThat(member.getGetOffTheBusDateTime()).isBefore(LocalDateTime.now()); // 하차 시간은 현재 시간보다 이전이다.
        assertThat(member.getGetOnTheBusDateTime()).isBefore(LocalDateTime.now()); // 승차 시간은 현재 시간보다 이전이다.
    }


    private Member createMember(int money) {
        return Member.builder()
                .id(1L)
                .name("스폰지밥")
                .money(money)
                .build();
    }

    private Bus createBus() {
        return Bus.builder().id(1L)
                .name("마을버스")
                .number(900)
                .build();
    }
}