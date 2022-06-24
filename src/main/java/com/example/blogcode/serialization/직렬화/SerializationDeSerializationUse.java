package com.example.blogcode.serialization.직렬화;

import java.io.*;

/**
 * packageName    : com.example.blogcode.serialization
 * fileName       : 직열화사용
 * author         : tkdwk567@naver.com
 * date           : 2022/06/23
 */

// 1. 직렬화를 사용할 땐 ObjectOutputStream 사용
// 2. 역직렬화를 사용할 땐 ObjectInputStream 사용
public class SerializationDeSerializationUse {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // InputStream 과 OutputStream 을 상속받은 클래스지만 기반 스트림을 인자로 줘야한다.

//        ObjectInputStream objectInputStream = new ObjectInputStream(); // 역직렬화
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(); // 직렬화

        // 파일에 객체를 직렬화 / 역직렬화 하고 싶다면 아래와 같이

        //  직렬화를 하고 싶다면? outputStream 을 사용하고 writeObject() 를 사용
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(new User());

        //  역직렬화를 하고 싶다면? InputStream 을 사용하고 readObject() 를 사용
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        //      반환 타입이 Object 타입이므로 형변환이 필요!!
        User user = (User) objectInputStream.readObject();



    }
}
