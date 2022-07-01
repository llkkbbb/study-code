package com.example.blogcode.interfacestduy;

/**
 * packageName    : com.example.blogcode.abstractstudy
 * fileName       : Tesla
 * author         : tkdwk567@naver.com
 * date           : 2022/07/01
 */
public class Tesla implements DrivingFunction {

    // fields....

    @Override
    public void changMode() {
        System.out.println("로직 변경....");
    }
}
