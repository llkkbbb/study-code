package com.example.blogcode.javabasic.serialization.직렬화;

import java.io.*;
import java.util.Base64;

/**
 * packageName    : com.example.blogcode.javabasic.serialization.직렬화
 * fileName       : UserSerialization
 * author         : tkdwk567@naver.com
 * date           : 2022/06/23
 */

// User 객체 직렬화 과정
public class SerializationFactory {

    // 직렬화 과정
    public static String serialization(Object object) throws IOException {
        byte[] serializationArr;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(object); // writeObject() 사용
                serializationArr = byteArrayOutputStream.toByteArray();
            }
        }

        return Base64.getEncoder().encodeToString(serializationArr);
    }

    // 역직렬화 과정
    public static Object deserialization(byte[] serializationArr) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializationArr)) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
                Object object = objectInputStream.readObject();// readObject() 사용
                return object;
            }
        }
    }
}
