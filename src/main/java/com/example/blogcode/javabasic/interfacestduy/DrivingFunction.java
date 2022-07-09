package com.example.blogcode.javabasic.interfacestduy;

/**
 * packageName    : com.example.blogcode.javabasic.interfacestduy
 * fileName       : DrivingFunction
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */
public interface DrivingFunction {

    // 테슬라 자율 주행 기능
    default void changMode() {
        System.out.println("자율 주행 기능 로직");
    }

}
