package com.example.blogcode.javabasic.exception;

/**
 * packageName    : com.example.blogcode.javabasic.exception
 * fileName       : MyException
 * author         : tkdwk567@naver.com
 * date           : 2022/07/15
 */
public class MyException extends RuntimeException {

    private final int ERROR_CODE;

    public MyException(int errorCode) {
        this.ERROR_CODE = errorCode;
    }

    public MyException(String message, int errorCode) {
        super(message);
        this.ERROR_CODE = errorCode;
    }

}
