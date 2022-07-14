package com.example.blogcode.javabasic.stream.domain;

import lombok.*;

/**
 * packageName    : com.example.blogcode.javabasic.stream.domain
 * fileName       : Customer
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String name;
    private int age;
    private int price;

}
