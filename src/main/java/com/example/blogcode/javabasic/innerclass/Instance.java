package com.example.blogcode.javabasic.innerclass;

/**
 * packageName    : com.example.blogcode.javabasic.innerclass
 * fileName       : Instance
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */
public class Instance {

    private int num = 10;
    private static int sNum = 20;
    private InClass inClass;

    public Instance() {
        this.inClass = new InClass();
    }

    private class InClass {
        int inNum = 30;
//        static int inSNum = 10;

        public void test() {
            System.out.println("외부 클래스의 num = " + num);
            System.out.println("외부 클래스의 sNum = " + sNum);
            System.out.println("내부 클래스의 inNum = " + inNum);
        }
    }

    public static class InStaticClass {
        int inNum = 50;
        static int inSNum = 60;

        public void test() {
//            System.out.println("외부 클래스의 num = " + num); // 인스턴스 변수는 사용하지 못함
            System.out.println("내부 클래스의 inNum = " + inNum);
            System.out.println("내부 클래스의 inSNum = " + inSNum);
            System.out.println("외부 클래스의 SNum" + sNum); // 외부 클래스의 정적 변수는 사용가능
        }

    }

    public void print() {
        this.inClass.test();
    }
}
