package com.example.blogcode.javabasic.polymorphism;

/**
 * packageName    : com.study.abstract1.다형성
 * fileName       : Main
 * author         : tkdwk567@naver.com
 * date           : 2022/06/30
 */
public class Main {

    public static void main(String[] args) {
        Employee kitchen = new Kitchen();
        Manager manager = new Manager();

//        manager.addEmployee(hall);
//        manager.addEmployee(kitchen);

//        manager.orderToDoJop();

        Employee hall = new Hall();
        hall.wellCome();
        kitchen.wellCome();



    }
}
