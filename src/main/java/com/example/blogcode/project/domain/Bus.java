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

    private final int payment = 1000;
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

    // 승차
    public Member getOn(Member member) {
        this.members.add(member);
        return member;
    }

    // 하차
    public Member getOff(Member member) {
        for (Member m : members) {
            if (m.getId() == member.getId()) {
                members.remove(member);
                break;
            }
        }
        return member;
    }

    // 승객수 반환
    public int getPassengers() {
        return this.members.size();
    }

    // 환승 체크
    public boolean checkTransfer(Member member) {
        if (isTransfer(member)) {
            return true;
        }

        return false;
    }

    private boolean isTransfer(Member member) {
        return member.getGetOffTheBusDateTime() != null
                && member.getGetOnTheBusDateTime().isBefore(member.getGetOffTheBusDateTime().plusSeconds(10));
    }
}
