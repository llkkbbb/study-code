package com.example.blogcode.effectivejava.item21;

/**
 * packageName    : com.example.blogcode.effectivejava.item21
 * fileName       : TestInterface
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */

public interface TestInterface {

    default int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

    public int minus(int firstNumber, int secondNumber);
}
