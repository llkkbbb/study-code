package com.example.blogcode.effectivejava.item18;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item18
 * fileName       : InstrumentedHashSetTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/22
 */
class InstrumentedHashSetTest {

    @Test
    void overriding_method() {
        class Item {
            private String name;

            public Item(String name) {
                this.name = name;
            }
        }
        InstrumentedHashSet<Item> hashSet = new InstrumentedHashSet<>();
        hashSet.addAll(List.of(new Item("안경"), new Item("옷"), new Item("모자")));
        assertThat(hashSet.getAddCount()).isEqualTo(3);
    }

    @Test
    void overriding_method1() {
        class Item {
            private String name;

            public Item(String name) {
                this.name = name;
            }
        }
        InstrumentedHashSet<Item> hashSet = new InstrumentedHashSet<>();
        hashSet.addAll(List.of(new Item("안경"), new Item("옷"), new Item("모자")));
        assertThat(hashSet.getAddCount()).isEqualTo(3);
    }

}