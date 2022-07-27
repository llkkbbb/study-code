package com.example.blogcode.effectivejava.item23;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.example.blogcode.effectivejava.item23
 * fileName       : Circle
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */

@Getter
@Builder
@AllArgsConstructor
public class Circle extends FigureSolution {

    private final int radius;

    @Override
    public int area() {
        return (int) Math.PI * (radius * radius);
    }
}
