package com.example.blogcode.effectivejava.item23;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.blogcode.effectivejava.item23
 * fileName       : Square
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
public class Square extends Rectangle {

    private final int side;

    public Square(int side) {
        super(side, side);
        this.side = side;
    }
}
