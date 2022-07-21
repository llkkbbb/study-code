package com.example.blogcode.effectivejava.item16;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * packageName    : com.example.blogcode.effectivejava.item16
 * fileName       : Point
 * author         : tkdwk567@naver.com
 * date           : 2022/07/21
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    // public 필드 말고 private 필드를 사용하라
    private int x;
    private int y;
}
