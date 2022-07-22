package com.example.blogcode.effectivejava.item18;

import java.util.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item18
 * fileName       : InstrumentedSet
 * author         : tkdwk567@naver.com
 * date           : 2022/07/22
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public static void main(String[] args) {
        Set<Integer> integers1 = new InstrumentedSet<>(new TreeSet<>());
        Set<Integer> integers2 = new InstrumentedSet<>(new HashSet<>());
    }
}
