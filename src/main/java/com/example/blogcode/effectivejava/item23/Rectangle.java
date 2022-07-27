package com.example.blogcode.effectivejava.item23;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.blogcode.effectivejava.item23
 * fileName       : Rectangle
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
@Builder
@AllArgsConstructor
public class Rectangle extends FigureSolution {

    private final int length;
    private final int width;

    @Override
    public int area() {
        return length * width;
    }
}
