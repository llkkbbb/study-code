package com.example.blogcode.javabasic.decorator;

/**
 * packageName    : com.example.blogcode.javabasic.decorator
 * fileName       : LightDecorator
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */
public class LightDecorator extends ChristmasTreeDecorator {

    public LightDecorator(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String decorator() {
        return super.decorator() + withLight();
    }

    private String withLight() {
        return "with light";
    }
}
