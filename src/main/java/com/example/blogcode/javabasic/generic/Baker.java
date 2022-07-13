package com.example.blogcode.javabasic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.example.blogcode.javabasic.generic
 * fileName       : Baker
 * author         : tkdwk567@naver.com
 * date           : 2022/07/13
 */

public class Baker {

    private List materials = new ArrayList<>();

    public void addMaterial(Material t) {
        // 재료 입고....
    }

    public Material getMaterial(int i) {
        return (Material) materials.get(i);
    }

//    public Bread makeBread(List<T> materials) {
//         빵 만드는 로직
//        return new Bread(materials.get(0));
//    }
}
