package com.example.blogcode.javabasic.lambda;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * packageName    : com.example.blogcode.javabasic.lambda
 * fileName       : Function
 * author         : tkdwk567@naver.com
 * date           : 2022/07/14
 */

@FunctionalInterface
public interface Function {

    public int sum(List<Integer> list);

}
