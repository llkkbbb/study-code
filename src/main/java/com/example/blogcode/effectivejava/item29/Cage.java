package com.example.blogcode.effectivejava.item29;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EmptyStackException;

public class Cage {
    private Object[] cages;
    private int size;
    private static int DEFAULT_SIZE = 10;

    public Cage() {
        this.cages = new Object[DEFAULT_SIZE];
    }

    public void push(Object o) {
        checkCapacity();
        this.cages[size++] = o;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = cages[--size];
        cages[size] = null;
        return result;
    }

    private void checkCapacity() {
        if (cages.length == size) {
            this.cages = Arrays.copyOf(this.cages, 2 * size + 1);
        }
    }
}
