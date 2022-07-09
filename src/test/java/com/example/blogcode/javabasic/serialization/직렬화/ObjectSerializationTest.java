package com.example.blogcode.javabasic.serialization.직렬화;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Base64;


/**
 * packageName    : com.example.blogcode.javabasic.serialization.직렬화
 * fileName       : ObjectSerializationTest
 * author         : tkdwk567@naver.com
 * date           : 2022/06/24
 */
class ObjectSerializationTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("직렬화 과정")
    void serialization() throws IOException {
        User user = new User(1L, "name", 10);

        String base64User = SerializationFactory.serialization(user);

        System.out.println(base64User);
    }

    @Test
    @DisplayName("역직렬화 과정 테스트 -> User 클래스 버전이 달라지면 InvalidClassException 에러 발생")
    byte[] deserialization() throws IOException, ClassNotFoundException {
        String base64User = "rO0ABXNyADFjb20uZXhhbXBsZS5ibG9nY29kZS5zZXJpYWxpemF0aW9uLuyngeugrO2ZlC5Vc2Vybi14ikL8dX4CAANJAANhZ2VMAAJpZHQAEExqYXZhL2xhbmcvTG9uZztMAARuYW1ldAASTGphdmEvbGFuZy9TdHJpbmc7eHAAAAAKc3IADmphdmEubGFuZy5Mb25nO4vkkMyPI98CAAFKAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cAAAAAAAAAABdAAEbmFtZQ==";
        byte[] deserializationArr = Base64.getDecoder().decode(base64User);
        User user = (User) SerializationFactory.deserialization(deserializationArr);
        String json = objectMapper.writeValueAsString(user);

        System.out.println("user Json size : " + json.getBytes().length);
//        System.out.println(user.toString());
        return deserializationArr;
    }

    @Test
    @DisplayName("용량 테스트")
    void sizeTest() throws IOException, ClassNotFoundException {
        byte[] deserializationArr = deserialization();
        System.out.println(deserializationArr.length);
    }

}