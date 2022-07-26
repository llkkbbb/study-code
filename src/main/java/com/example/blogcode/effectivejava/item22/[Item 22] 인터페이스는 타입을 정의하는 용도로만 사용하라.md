# [Item 22] 인터페이스는 타입을 정의하는 용도로만 사용하라

인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.

달리 말해, 클래스가 어떤 인터페이스를 구현한다는 것은 자신의 인스턴스로 무엇을 할 수 있는지를 클라이언트에 얘기해주는 것이다. 인터페이스는 오직 이 용도로만 사용해야 한다.

### 상수 인터페이스 안티패턴

```java
public interface PhysicalConstants {
    static final intFIRST= 1;
    static final intSECOND= 2;
    static final intTHREE= 3;
}
```

이러한 인터페이스는 안티 패턴이므로 사용하지 말자.

- 클래스 내부에서 사용하는 상수는 외부 인터페이스가 아니라 내부 구현에 해당한다.
- 클라이언트 코드가 내부 구현에 해당하는 이 상수들에 종속되게 한다.

### 상수를 공개하는 방법

- 상수 유틸리티 클래스

```java
// 인스턴스화 방지
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhysicalConstantsSolution1 {
    public static final intFIRST = 1;
    public static final intSECOND = 1;
    public static final intTHREE = 1;
}
```

클라이언트에서 상수를 사용하려면 클래스 이름까지 함계 명시해야한다.

- 정적 임포트를 사용해 상수 이름만으로 사용하기

```java
import static com.example.blogcode.effectivejava.item22.PhysicalConstantsSolution1.*;

public class Test {
    int add(int first, int second) {
        return FIRST+SECOND;
    }
}
```

해당 클래스의 상수를 빈번히 사용한다면 정적 임포트가 값어치를 한다.

### 정리

- 인터페이스는 타입을 정의하는 용도로만 사용해야 한다.
- 상수 공개용 수단으로 사용하지 말자.