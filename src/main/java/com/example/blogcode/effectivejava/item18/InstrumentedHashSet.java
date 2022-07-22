package com.example.blogcode.effectivejava.item18;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

/**
 * packageName    : com.example.blogcode.effectivejava.item18
 * fileName       : InstrumentedHashSet
 * author         : tkdwk567@naver.com
 * date           : 2022/07/22
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean flag = false;
        for (E e : c) {
            add(e);
            flag = true;
        }
        return flag;
    }

//    @Override
//    public boolean addAll(Collection<? extends E> c) {
//        addCount += c.size();
//        return super.addAll(c);
//    }
}
