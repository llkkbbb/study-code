package com.example.blogcode.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.domain
 * fileName       : CustomerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/13
 */

class CustomerTest {

    @Test
    @DisplayName("Customer 객체 생성")
    void CustomerTest() {
        Customer customer = Customer.builder()
                .name("스폰지밥")
                .age(11)
                .bonusPoint(1000)
                .build();

        System.out.println(customer.toString());
    }

}