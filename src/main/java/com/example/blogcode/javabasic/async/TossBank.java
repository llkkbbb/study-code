package com.example.blogcode.javabasic.async;

import com.example.blogcode.javabasic.async.exception.InputMoneyException;
import com.example.blogcode.javabasic.async.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Synchronized;
import org.springframework.scheduling.annotation.Async;

import java.time.format.DecimalStyle;

/**
 * packageName    : com.example.blogcode.javabasic.async
 * fileName       : Bank
 * author         : tkdwk567@naver.com
 * date           : 2022/07/30
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TossBank {

    private int money;


    public synchronized int credit(int money) throws InterruptedException {
        int currentMoney = getMoney();
        if (money <= 0) {
            throw new InputMoneyException();
        } else {
            Thread.sleep(2000);
            updateMoney(currentMoney + money);
        }
        return money;
    }


    @Async
    public synchronized int withdraw(int money) throws InterruptedException {
        int currentMoney = getMoney();

        if (currentMoney == 0) {
            throw new NotEnoughMoneyException();
        } else if (money == 0) {
            throw new InputMoneyException();
        } else {
            Thread.sleep(1000);
            updateMoney(currentMoney - money);
        }
        return money;
    }

    public void updateMoney(int money) {
        this.money = money;
    }
}
