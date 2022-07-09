package com.example.blogcode.effectivejava.item10;

/**
 * packageName    : com.example.blogcode.effectivejava.item10
 * fileName       : ColorPoint
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
public class ColorPoint extends Point {

    private Color color;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    /***
     * 대치성 위배
     * equals() 정의하지 않는 경우
     */


    /***
     * 추이성 위배
     * 색상까지 비교하는 경우
     */
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof ColorPoint)) return false;
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }

    /***
     * 추이성 위배, 대치성 성립
     * 색상을 무시하고 비교
     */
//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof Point)) return false;
//
//        // o 가 ColorPoint 타입이 아니라면 색상을 무시하고 Point 만 비교
//        if (!(o instanceof ColorPoint)) return o.equals(this);
//
//        // o 가 ColorPoint 타입이라면 색상까지 비교
//        return super.equals(o) && ((ColorPoint) o).color == color;
//    }



}