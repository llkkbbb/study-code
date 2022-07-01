package com.example.blogcode.project.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.example.blogcode.project.domain
 * fileName       : Bus
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */

@Getter
public class Bus {

    private final int PAYMENT = 1000;
    private Long id;
    private String name;
    private int number;
    private List<Member> members = new ArrayList<>();

    public Bus() {
    }

    @Builder
    public Bus(Long id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
}
