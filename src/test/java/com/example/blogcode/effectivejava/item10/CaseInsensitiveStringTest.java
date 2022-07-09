package com.example.blogcode.effectivejava.item10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item10
 * fileName       : CaseInsensitiveStringTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
class CaseInsensitiveStringTest {

    @Test
    @DisplayName("대치성이 위배된다")
    void 대치성테스트1() {
        CaseInsensitiveString cis = new CaseInsensitiveString("Hello");
        String s = "Hello";

        assertThat(cis.equals(s)).isTrue();
        assertThat(s.equals(cis)).isFalse();

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(cis);

        assertThat(list.contains(s)).isFalse();
    }

}