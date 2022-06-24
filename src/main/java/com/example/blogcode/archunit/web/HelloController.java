package com.example.blogcode.archunit.web;

import com.example.blogcode.archunit.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * packageName    : com.example.blogcode
 * fileName       : HelloController
 * author         : tkdwk567@naver.com
 * date           : 2022/06/17
 */

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

}
