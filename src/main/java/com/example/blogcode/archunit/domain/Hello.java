package com.example.blogcode.archunit.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * packageName    : com.example.blogcode.archunit.domain
 * fileName       : Hello
 * author         : tkdwk567@naver.com
 * date           : 2022/06/17
 */

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hello {

    @Id @GeneratedValue
    private Long id;

    private String hello;
}
