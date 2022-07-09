package com.example.blogcode.javabasic.callby;

/**
 * packageName    : com.example.blogcode.javabasic.callby
 * fileName       : CallByReference
 * author         : tkdwk567@naver.com
 * date           : 2022/06/24
 */

public class CallByReference {
    public static void nameSwap(Person personA, Person personB) {
        String nameA = personA.getName();
        personA.setName(personB.getName());
        personB.setName(nameA);
    }

    public static void main(String[] args) {
        Person personA = new Person("스폰지밥", 20);
        Person personB = new Person("뚱이", 21);

        System.out.println("swap 하기 전");
        System.out.println("personA = " + personA.getName() + " | personB = " + personB.getName() + "\n");

        nameSwap(personA, personB);
        System.out.println("swap 후");
        System.out.println("personA = " + personA.getName() + " | personB = " + personB.getName() + "\n");
    }
}
