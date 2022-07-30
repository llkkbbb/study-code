package com.example.blogcode.javabasic.async;

/**
 * packageName    : com.example.blogcode.javabasic.async
 * fileName       : Child
 * author         : tkdwk567@naver.com
 * date           : 2022/07/31
 */
public class Child extends Thread {

    private TossBank tossBank;
    private int money;

    public Child(TossBank tossBank, int money) {
        this.tossBank = tossBank;
        this.money = money;
    }

    @Override
    public void run() {
        try {
            tossBank.withdraw(money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("출금합니다." + this.money);
    }
}
