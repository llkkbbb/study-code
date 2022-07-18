package com.example.blogcode.javabasic.decorator;

/**
 * packageName    : com.example.blogcode.javabasic.decorator
 * fileName       : BubbleDecorator
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */
public class BubbleDecorator extends ChristmasTreeDecorator {

    public BubbleDecorator(ChristmasTree tree) {
        super(tree);
    }

    @Override
    public String decorator() {
        return super.decorator() + withBubble();
    }

    private String withBubble() {
        return "with bubble decorator";
    }
}
