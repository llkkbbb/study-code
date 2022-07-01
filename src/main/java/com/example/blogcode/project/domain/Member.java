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
}
