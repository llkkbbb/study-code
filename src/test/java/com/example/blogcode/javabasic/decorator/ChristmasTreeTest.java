package com.example.blogcode.javabasic.decorator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.javabasic.decorator
 * fileName       : ChristmasTreeTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/18
 */
class ChristmasTreeTest {

    @Test
    @DisplayName("decorator pattern test")
    void ChristmasTreeTest() {
        ChristmasTree tree1 = new ChristmasTreeImpl();
        assertThat(tree1.decorator()).isEqualTo("Christmas ");

        ChristmasTree tree2 = new BubbleDecorator(new ChristmasTreeImpl());
        assertThat(tree2.decorator()).isEqualTo("Christmas with bubble decorator");

        ChristmasTree tree3 = new LightDecorator(new ChristmasTreeImpl());
        assertThat(tree3.decorator()).isEqualTo("Christmas with light");
    }
}