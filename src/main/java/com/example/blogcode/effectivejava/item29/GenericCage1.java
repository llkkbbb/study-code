package com.example.blogcode.effectivejava.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class GenericCage1<E> {
    private Object[] cages;
    private int size;
    private static int DEFAULT_SIZE = 10;

    public GenericCage1() {
        this.cages = new Object[DEFAULT_SIZE];
    }

    public void push(E o) {
        checkCapacity();
        this.cages[size++] = o;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked") E result = (E) cages[--size];
        cages[size] = null;
        return result;
    }

    private void checkCapacity() {
        if (cages.length == size) {
            this.cages = Arrays.copyOf(this.cages, 2 * size + 1);
        }
    }
}
