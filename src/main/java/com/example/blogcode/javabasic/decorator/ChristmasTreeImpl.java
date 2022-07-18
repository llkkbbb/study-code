package com.example.blogcode.javabasic.decorator;

import org.apache.catalina.authenticator.jaspic.CallbackHandlerImpl;

/**
 * packageName    : com.example.blogcode.javabasic.decorator
 * fileName       : ChristmasTreeImpl
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */
public class ChristmasTreeImpl implements ChristmasTree {

    @Override
    public String decorator() {
        return "Christmas ";
    }
}
