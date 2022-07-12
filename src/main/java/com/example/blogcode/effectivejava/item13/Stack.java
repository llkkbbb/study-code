package com.example.blogcode.effectivejava.item13;

/**
 * packageName    : com.example.blogcode.effectivejava.item13
 * fileName       : Stack
 * author         : tkdwk567@naver.com
 * date           : 2022/07/12
 */
public class Stack implements Cloneable {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public Object[] getElements() {
        return elements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    @Override
//    public Stack clone() {
//        try {
//            return (Stack) super.clone();
//        } catch (CloneNotSupportedException e) {
//            throw new AssertionError();
//        }
//    }

    @Override
    public Stack clone() {
        try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
