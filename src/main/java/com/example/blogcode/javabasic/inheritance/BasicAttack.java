package com.example.blogcode.javabasic.inheritance;

/**
 * packageName    : com.example.blogcode.javabasic.inheritance
 * fileName       : BasicAttack
 * author         : tkdwk567@naver.com
 * date           : 2022/07/09
 */
public interface BasicAttack extends Skill{
    default void attack(String weapon) {
        // 무기에 따라 달라지는 로직!
    };
}
