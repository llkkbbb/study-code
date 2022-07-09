package com.example.blogcode.effectivejava.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item10
 * fileName       : ColorPointTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
class ColorPointTest {

    @Test
    @DisplayName("ColorPoint equals 메서드를 재정의 하지 않은 경우 -> 추이성은 성립되는데 색깔은 무시하여 비교하게 된다")
    void 추이성테스트1() {
        ColorPoint p1 = new ColorPoint(0, 1, Color.RED);
        Point p2 = new Point(0, 1);
        ColorPoint p3 = new ColorPoint(0, 1, Color.YELLOW);

        assertThat(p1.equals(p2)).isTrue();
        assertThat(p2.equals(p3)).isTrue();
        assertThat(p1.equals(p3)).isTrue();
    }

    @Test
    @DisplayName("ColorPoint equals 메서드를 재정의 한 경우 -> 색상까지 비교하는 경우 -> 추이성 위배!")
    void 추이성테스트2() {
        ColorPoint p1 = new ColorPoint(0, 1, Color.RED);
        Point p2 = new Point(0, 1);
        ColorPoint p3 = new ColorPoint(0, 1, Color.YELLOW);

        assertThat(p1.equals(p2)).isFalse();
        assertThat(p2.equals(p3)).isTrue();
        assertThat(p1.equals(p3)).isFalse();
    }

    @Test
    @DisplayName("ColorPoint equals 메서드를 재정의 한 경우 -> 색상을 무시하고 비교 -> 추이성 위배! , 대치성은 성립된다.")
    void 추이성테스트3() {
        ColorPoint p1 = new ColorPoint(0, 1, Color.RED);
        Point p2 = new Point(0, 1);
        ColorPoint p3 = new ColorPoint(0, 1, Color.YELLOW);

        // 추이성 위배
        assertThat(p1.equals(p2)).isTrue();
        assertThat(p2.equals(p3)).isTrue();
        assertThat(p1.equals(p3)).isFalse();

        // 색상을 무시하면 대치성 성립
        assertThat(p2.equals(p1)).isTrue();
        assertThat(p1.equals(p2)).isTrue();
    }
}