package com.example.blogcode.javabasic.async;

/**
 * packageName    : com.example.blogcode.javabasic.async
 * fileName       : Parent
 * author         : tkdwk567@naver.com
 * date           : 2022/07/30
 */
public class Parent extends Thread {
    private TossBank tossBank;
    private int money;

    public Parent(TossBank tossBank, int money) {
        this.tossBank = tossBank;
        this.money = money;
    }

    @Override
    public void run() {
        try {
            tossBank.credit(this.money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("입금합니다." + this.money);
    }
}
