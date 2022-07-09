package com.example.blogcode.javabasic.archunit.service;

import com.example.blogcode.javabasic.archunit.domain.HelloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.example.blogcode.javabasic.archunit.service
 * fileName       : HelloService
 * author         : tkdwk567@naver.com
 * date           : 2022/06/17
 */

@Service
@RequiredArgsConstructor
public class HelloService {

    private final HelloRepository helloRepository;

}
