package com.example.blogcode.effectivejava.item14;

import lombok.Getter;
import org.springframework.context.annotation.Primary;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Comparator;

/**
 * packageName    : com.example.blogcode.effectivejava.item14
 * fileName       : PhoneNumber
 * author         : tkdwk567@naver.com
 * date           : 2022/07/19
 */
public class PhoneNumber implements Comparable<PhoneNumber> {

    private int firstNumber;
    private int middleNumber;
    private int lastNumber;

//    static Comparator<Object> hashCodeOrder = new Comparator<Object>() {
//        @Override
//        public int compare(Object o1, Object o2) {
//            return o1.hashCode() - o2.hashCode();
//        }
//    };

//    static Comparator<Object> hashCodeOrder = new Comparator<Object>() {
//        @Override
//        public int compare(Object o1, Object o2) {
//            return Integer.compare(o1.hashCode(), o2.hashCode());
//        }
//    };

    static Comparator<Object> hashCodeOrder = Comparator.comparingInt(Object::hashCode);

    private static final Comparator<PhoneNumber> COMPARATOR =
            Comparator.comparingInt((PhoneNumber pn) -> pn.firstNumber)
                    .thenComparingInt(pn -> pn.middleNumber)
                    .thenComparingInt(pn -> pn.lastNumber);

    @Override
    public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }



//    @Override
//    public int compareTo(PhoneNumber pn) {
//        int result = Integer.compare(firstNumber, pn.firstNumber); // 가장 중요
//        if (result == 0) {
//            result = Integer.compare(middleNumber, pn.middleNumber); // 두 번째 중요
//            if (result == 0) {
//                result = Integer.compare(lastNumber, pn.lastNumber); // 세 번째 중요
//            }
//        }
//        return result;
//    }


}
