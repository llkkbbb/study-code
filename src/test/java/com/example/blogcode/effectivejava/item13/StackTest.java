package com.example.blogcode.effectivejava.item13;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item13
 * fileName       : StackTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */
class StackTest {

    @Test
    @DisplayName("stack 객체 복제 후 elements 배열은 서로 달라야 하는데 같은 참조값의 주소를 가르키니 서로 같은 배열이다.")
    void StackTest() throws CloneNotSupportedException {
        Stack stack = new Stack();
        Stack cloneStack = stack.clone();

        assertEquals(stack.getElements(), cloneStack.getElements());
    }

}