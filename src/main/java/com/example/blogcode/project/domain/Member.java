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

    public Member getOnBus(Bus bus) {
        checkMoney(bus);
        this.getOnTheBusDateTime = LocalDateTime.now();
        Member member = bus.getOn(this);
        return member;
    }

    private void checkMoney(Bus bus) {
        if (this.money < bus.getPayment()) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }

        this.money -= bus.getPayment();
    }


}
