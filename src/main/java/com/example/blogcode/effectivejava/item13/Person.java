package com.example.blogcode.effectivejava.item13;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.AssertionFailure;

/**
 * packageName    : com.example.blogcode.effectivejava.item13
 * fileName       : Person
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */

@Getter
@Setter
public class Person{

    private String name;
    private int age;

    public Person(Person person) {
        this.name = person.getName();
        this.age = person.getAge();
    }

    public static Person newInstance(Person person) {
        return new Person(person);
    }

    //    @Override
//    public Person clone() {
//        try {
//            return (Person) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }
}
