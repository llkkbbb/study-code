package com.example.blogcode.javabasic.decorator;

/**
 * packageName    : com.example.blogcode.javabasic.decorator
 * fileName       : ChristmasTreeDecorator
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */
public abstract class ChristmasTreeDecorator implements ChristmasTree {

    private ChristmasTree tree;

    public ChristmasTreeDecorator(ChristmasTree tree) {
        this.tree = tree;
    }

    @Override
    public String decorator() {
        return tree.decorator();
    }
}
