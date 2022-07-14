package com.example.blogcode.javabasic.stream.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.javabasic.stream.domain
 * fileName       : CustomerTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
class CustomerTest {

    @Test
    @DisplayName("stream 으로 연산해보기")
    void stream_with_customer() {
        Customer customer1 = Customer.builder()
                .name("스폰지밥")
                .age(21)
                .price(100000)
                .build();

        Customer customer2 = Customer.builder()
                .name("다람이")
                .age(17)
                .price(10000)
                .build();

        Customer customer3 = Customer.builder()
                .name("뚱이")
                .age(23)
                .price(15000)
                .build();

        Customer customer4 = Customer.builder()
                .name("집게사장")
                .age(42)
                .price(20000)
                .build();

        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);

        // 고객 명단 출력
        List<String> customerNames = customers.stream()
                .map(Customer::getName)
                .collect(Collectors.toList());

        // 고객 여행 비용
        int totalPrice = customers.stream()
                .mapToInt(Customer::getPrice)
                .sum();

        // 20세 이상 고객 // 내림차순 정렬
        List<Customer> collect = customers.stream()
                .filter(c -> c.getAge() >= 20)
                .sorted(Comparator.comparing(Customer::getName).reversed())
                .collect(Collectors.toList());

        for (Customer customer : collect) {
            System.out.println(customer.getName());
        }
    }
}