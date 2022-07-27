package com.example.blogcode.effectivejava.item23;

/**
 * packageName    : com.example.blogcode.effectivejava.item23
 * fileName       : Figure
 * author         : tkdwk567@naver.com
 * date           : 2022/07/27
 */
public class Figure {

    enum Shape {RECTANGLE, CIRCLE}

    private final Shape shape;
    private int length;
    private int width;
    private int radius;

    public Figure(int radius) {
        this.shape = Shape.CIRCLE;
        this.radius = radius;
    }

    public Figure(int length, int width) {
        this.shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    public int area() {
        switch (shape) {
            case CIRCLE:
                return (int) Math.PI * (radius * radius);
            case RECTANGLE:
                return length * width;
            default:
                throw new AssertionError("원이나 사각형이 아니에요");
        }
    }
}
