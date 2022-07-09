package com.example.blogcode.effectivejava.item10;

import java.util.Set;

/**
 * packageName    : com.example.blogcode.effectivejava.item10
 * fileName       : Point
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
public class Point {

    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Point)) return false;
//        Point p = (Point) o;
//        return p.x == x && p.y == y;
//    }


    /***
     * 리스코프 치환 원칙 위배
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }
}
