package com.example.blogcode.effectivejava.item21;

import org.aspectj.weaver.ast.Test;

/**
 * packageName    : com.example.blogcode.effectivejava.item21
 * fileName       : Calcurator
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */
public class Calculator implements TestInterface {

    @Override
    public  int minus(int firstNumber, int secondNumber) {
        return firstNumber - secondNumber;
    }

    @Override
    public synchronized int add(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }
}
