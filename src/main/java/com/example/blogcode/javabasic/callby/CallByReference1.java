package com.example.blogcode.javabasic.callby;

/**
 * packageName    : com.example.blogcode.javabasic.callby
 * fileName       : CallByReference1
 * author         : tkdwk567@naver.com
 * date           : 2022/06/24
 */
public class CallByReference1 {
    public static void printAddress(Person personA, Person personB) {
        Person temp = personA;
        personA = personB;
        personB = temp;
        System.out.println("swap 메서드 내부");
        System.out.println("personA = " + personA);
        System.out.println("personB = " + personB + "\n");
    }

    public static void main(String[] args) {
        Person personA = new Person("스폰지밥", 20);
        Person personB = new Person("뚱이", 21);


        System.out.println("swap 전");
        System.out.println("personA = " + personA);
        System.out.println("personB = " + personB + "\n");
        printAddress(personA, personB);

        System.out.println("swap 후");
        System.out.println("personA = " + personA);
        System.out.println("personB = " + personB);


    }

}
