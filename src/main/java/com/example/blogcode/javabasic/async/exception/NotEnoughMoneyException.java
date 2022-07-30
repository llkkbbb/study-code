package com.example.blogcode.javabasic.async.exception;

/**
 * packageName    : com.example.blogcode.javabasic.async.exception
 * fileName       : NotEnoughMoneyException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/31
 */
public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
