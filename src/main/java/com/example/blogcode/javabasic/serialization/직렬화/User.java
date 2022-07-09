package com.example.blogcode.javabasic.serialization.직렬화;

import lombok.*;

import java.io.Serializable;

/**
 * packageName    : com.example.blogcode.javabasic.serialization
 * fileName       : User
 * author         : tkdwk567@naver.com
 * date           : 2022/06/23
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {
    // Serializable 을 구현한 클래스를 상속받는다면 상속받은 클래스는 Serializable 을 구현하지 않아도 직렬화가 가능하다.

    // 1. SerialVersionUID 으로 클래스의 버전을 관리한다.
    // 2. 해당값이 없으면 기본으로 해쉬값으로 관리된다.
    // 3. 직렬화를 한 상태에서 해당 객체의 필드를 추가 즉, 버전이 변경되면 InvalidClassException 발생! 그러므로 SerialVersionUID 으로 관리 되어야 한다.
//    private static final long serialVersionUID = -4255938668293970668L;

    private Long id;
    private String name;
    // 새로운 필드 추가
//    private String firstName;
    private int age;

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
