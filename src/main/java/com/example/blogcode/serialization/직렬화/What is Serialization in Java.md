# What is Serialization in Java?

### 직렬화란?

- JVM 메모리 내 스택과 힙영역에 저장되어 있는 객체를 외부에서 사용할 수 있도록 Byte 형태로 변환하는 과정을 직렬화라고 한다.
- 직렬화와 역직렬화를 통칭하여 직렬화 기술이라 말한다.

### 사용하는 이유?

서로 다른 가상메모리 공간에서 데이터를 통신하고 싶다. 참조값(주소값) 으로 전달하면 사용할 수 있을까? 

다른 환경에서 주소값을 알고 있어도 독립적인 특징을 가지고 있는 가상메모리에서 의미가 없어진다.

하지만 직렬화를 사용하여 데이터를 바이트 형태로 변환하고 전달하면 받는 쪽에서 역직렬화를 하여 데이터를 얻어올 수 있다.

### 직렬화의 조건

- implement `Serialization`
- 상위 클래스가 Serialization 인터페이스를 구현 한 경우
    - 상속관계의 클래스 중 `상위 클래스`가 Serialization 인터페이스를 구현한 경우 상속관계 클래스 전부 직렬화 대상이 된다.
- 하위 클래스가 Serialization 인터페이스를 구현 한 경우
    - 상속관계의 클래스 중 `하위 클래스`가 Serialization 인터페이스를 구현한 경우 하위 클래스만 직렬화 대상이 되고 상위 클래스는 제외가 된다.
- 모든 클래스의 상위 클래스인 Object 클래스는 `Serialization` 를 구현한 클래스가 아니므로 직렬화 대상이 아니다. Object 참조가 있으면 예약어 `transient` 를 사용하자. 이 뿐만 아니라 직렬화 대상을 제외 하고 싶은 필드에 사용할 수 있다.

### 직렬화 과정

직렬화 대상의 클래스는 SerialVersionUID 로 버전 관리가 되어야 한다. 지금은 SerialVersionUID 를 제외하고 과정을 소스코드로 옮겨보겠습니다.

직렬화 대상으로 Serializable 구현한 User 클래스 

```java
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private Long id;
    private String name;
    private int age;

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
```

직렬화 기능을 가진 SerializationFactory 클래스입니다.

```java
public class SerializationFactory {

    // 직렬화 과정
    public static String serialization(Object object) throws IOException {
        byte[] serializationArr;

        try (ByteArrayOutputStream byteArrayOutputStream =
															 new ByteArrayOutputStream()) {
            try (ObjectOutputStream objectOutputStream =
														 new ObjectOutputStream(byteArrayOutputStream)) {
                objectOutputStream.writeObject(object); // writeObject() 사용
                serializationArr = byteArrayOutputStream.toByteArray();
            }
        }

        return Base64.getEncoder().encodeToString(serializationArr);
    }

    // 역직렬화 과정
    public static Object deserialization(byte[] serializationArr) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream =
															 new ByteArrayInputStream(serializationArr)) {
            try (ObjectInputStream objectInputStream = 
																	new ObjectInputStream(byteArrayInputStream)) {
                Object object = objectInputStream.readObject();// readObject() 사용
                return object;
            }
        }
    }
}

```

- 직렬화

ObjectOutputStream 으로 직렬화를 하고 기반 스트림으로는 ByteArrayOutputStream 을 사용한다.

writeObject() 로 object를 받고 직렬화 작업을 한다. 다음으로 byte 형태의 배열에 저장한다.

반환값으로 Base64 타입의 인코딩한 값을 반환한다.

인코딩값

```java
rO0ABXNyADFjb20uZXhhbXBsZS5ibG9nY29kZS5zZXJpYWxpemF0......생략
```

- 역직렬화

ObjectInputStream 으로 역직렬화를 하고 기반 스트림으로는 ByteArrayOutputStream 을 사용한다.

readObject() 로 stream에 쓰여진 object를 읽어고 반환값으로 object를 반환한다.

### 왜 SerialVersionUID로 클래스 버전 관리를 해줘야 할까?

위에서 직렬화 과정으로 인코딩 한값을 가지고 테스트를 해보자.

```java
		@Test
    @DisplayName("역직렬화 과정 테스트 -> User 클래스 버전이 달라지면 InvalidClassException 에러 발생")
    void deserialization() throws IOException, ClassNotFoundException {
        String base64User = "rO0ABXNyADFjb20uZXhhbXBsZS5ibG9nY29kZS5zZXJpYWxpemF0......생략";
        byte[] deserializationArr = Base64.getDecoder().decode(base64User);
        User user = (User) SerializationFactory.deserialization(deserializationArr);

        System.out.println(user.toString());
    }

}
```

직렬화 수행으로 얻은 인코딩값으로 역직렬화 과정 테스트를 진행한다. 이 결과 테스트는 성공이다.

하지만 직렬화 대상 클래스의 버전이 달라진다면?

```java
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private Long id;
    private String name;
    // 새로운 필드 추가
    private String firstName;
    private int age;

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
```

InvalidClassException 에러가 발생된다.

```java
java.io.InvalidClassException: com.example.blogcode.serialization.직렬화.User;
local class incompatible: stream classdesc serialVersionUID = 7939134253348713854,
local class serialVersionUID = -6175310812803449725
```

직렬화 과정을 수행할 때 클래스 버전이 변경 되었나 해쉬값이나 serialVersionUID 으로 비교하여 확인하고 직렬화 작업이 진행된다는 것이다.

해쉬값은 클래스가 변경되면 해쉬값도 변하기 때문에 안정적이지 못하다. 이러한 이유로 serialVersionUID 를 고정 값으로 사용하여 관리해야된다.

### 직렬화 과정의 결론

1. 개발자가 직접 컨트롤이 가능한 클래스의 객체가 아닌 클래스의 객체에 대해서는 직렬화를 지양해야한다.
2. 역직렬화가 되지 않을 때의 예외처리는 꼭 필히! 해야한다.
3. 직렬화의 대상이 자주 변경되는 대상이면 직렬화를 지양해야한다.
4. 직렬화 데이터는 해당 클래스의 메타정보를 가지고 있으므로 용량이 크다. JSON 포맷을 사용하는 것이 효율적 

참고 : 

[https://techblog.woowahan.com/2551/](https://techblog.woowahan.com/2551/)