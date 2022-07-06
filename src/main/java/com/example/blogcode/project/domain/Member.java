package com.example.blogcode.project.domain;

import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;

/**
 * packageName    : com.example.blogcode.project.domain
 * fileName       : Member
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */

@Getter
public class Member {

    private Long id;
    private String name;
    private int money;
    private LocalDateTime getOnTheBusDateTime;
    private LocalDateTime getOffTheBusDateTime;

    @Builder
    public Member(Long id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    // 버스 승차
    public Member getOnBus(Bus bus) {
        checkMoney(bus);
        busPayment(bus);
        this.getOnTheBusDateTime = LocalDateTime.now();

        // 환승 로직
        if (bus.checkTransfer(this)) {
            System.out.println("환승입니다.");
            this.money += bus.getPayment();
        }

        return bus.getOn(this);
    }

    // 잔액 확인
    private void checkMoney(Bus bus) {
        if (this.money < bus.getPayment()) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
    }

    // 버스 비용 지불
    private void busPayment(Bus bus) {
        this.money -= bus.getPayment();
    }

    // 버스 하차
    public void getOffBus(Bus bus) {
        this.getOffTheBusDateTime = LocalDateTime.now();
        bus.getOff(this);
    }

}
