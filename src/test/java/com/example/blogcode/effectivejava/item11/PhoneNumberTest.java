package com.example.blogcode.effectivejava.item11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.example.blogcode.effectivejava.item11
 * fileName       : PhoneNumberTest
 * author         : tkdwk567@naver.com
 * date           : 2022/07/10
 */
class PhoneNumberTest {

    @Test
    @DisplayName("map에 new 인스턴스를 저장 후 new 인스턴스로 value 값 가져오기 테스트 실패")
    void PhoneNumberTest() {
        Map<PhoneNumber, String> map = new HashMap<>();
        map.put(new PhoneNumber(010, 1234, 4567), "스폰지밥");

        String result = map
                .get(new PhoneNumber(010, 1234, 4567));

        int hashCode = new PhoneNumber(010, 1234, 4567).hashCode();
        int hashCode1 = new PhoneNumber(010, 1234, 4567).hashCode();
        System.out.println(hashCode == hashCode1);


//        assertThat(result).isNotEqualTo("스폰지밥");
        assertThat(result).isEqualTo("스폰지밥");
    }

}