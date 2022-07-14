package com.example.blogcode.javabasic.innerclass;

/**
 * packageName    : com.example.blogcode.javabasic.innerclass
 * fileName       : Outer
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class Outer {

    Runnable runnable() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("익명 클래스 호출");
            }
        };
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("나를 불럿나?");
        }
    };


    Runnable getRunnable(int i) {
        int num = 100;

        class MyRunnable implements Runnable {

            int localNum = 10;
            @Override
            public void run() {
//                num = 200; // 컴파일 에러! 상수로 바뀜
//                i = 20; // 컴파일 에러! 상수로 바뀜

            }
        }
        return new MyRunnable();
    }

}
