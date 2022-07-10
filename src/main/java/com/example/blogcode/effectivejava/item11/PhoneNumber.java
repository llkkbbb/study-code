package com.example.blogcode.effectivejava.item11;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.NamedAttributeNode;
import java.util.Objects;

/**
 * packageName    : com.example.blogcode.effectivejava.item11
 * fileName       : PhoneNumber
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */

@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {

    private int firstNumber;
    private int middleNumber;
    private int lastNumber;

    private int hashCode;

    public PhoneNumber(int firstNumber, int middleNumber, int lastNumber) {
        this.firstNumber = firstNumber;
        this.middleNumber = middleNumber;
        this.lastNumber = lastNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(firstNumber, that.firstNumber) && Objects.equals(middleNumber, that.middleNumber) && Objects.equals(lastNumber, that.lastNumber);
    }

    // 전형적인 hash 메서드
    //    @Override
//    public int hashCode() {
//        int result = Integer.hashCode(firstNumber);
//        result = 31 * result + Integer.hashCode(middleNumber);
//        result = 31 * result + Integer.hashCode(lastNumber);
//        return result;
//    }

    // 한줄 메서드
//    @Override
//    public int hashCode() {
//        return Objects.hash(firstNumber, middleNumber, lastNumber);
//    }

    // 지연 초기화
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Integer.hashCode(firstNumber);
            result = 31 * result + Integer.hashCode(middleNumber);
            result = 31 * result + Integer.hashCode(lastNumber);
            hashCode = result;
        }
        return result;
    }
}
