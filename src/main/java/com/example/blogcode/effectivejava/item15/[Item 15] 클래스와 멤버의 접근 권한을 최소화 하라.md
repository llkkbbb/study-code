# [Item 15] 클래스와 멤버의 접근 권한을 최소화하라

어설프게 설계한 컴포넌트와 잘 설계된 컴포넌트의 차이점은 내부 데이터를 얼마나 잘 숨겼냐에 따른다.

캡슐화 혹은 정보 은닉이 설계의 기반이되는 원리이다.

### 캡슐화의 장점

- 여러 컴포넌트를 병렬로 개발이 가능하므로 시스템 개발 속도를 높인다.
- 각 컴포넌트를 빠르게 파악할 수 있고 시스템 교체하는 부담도 적기 때문에 시스템 관리 비용을 낮춘다.
- 캡슐화 자체만으로 성능을 높여주지 않지만 각 컴포넌트에 영향을 주지 않고 해당 컴포넌트만을 최적화 할 수 있기 때문에 성능 최적화에 도움을 준다.
- 소프트웨어 재사용을 높인다.
- 개발 난이도를 낮춰준다.

캡슐화의 접근성은 접근제어자로 정해진다.

### 기본원칙

- 모든 클래스와 멤버의 접근성을 가능한 한 좁혀야 한다.
- 패키지 외부에서 쓸 이유가 없으면 package-private으로 선언하자. (default)
    - api가 아닌 내부 구현이 되어 언제든 수정이 가능하다.
    - public으로 선언한 경우 하위 호환을 위해 영원히 관리해줘야 한다.
- package-private으로 클래스나 인터페이스를 생성하고 private static으로 중첩시키면 바깥 클래스 하나에서만 접근할 수 있다.

### 접근제어자

- private : 멤버를 선언한 톱레벨 클래스에서만 접근할 수 있다.
- package-private (default) : 멤버가 소속된 패키지 안의 모든 클래스에서 접근할 수 있다.
- protected : package-private 접근 범위를 포함하며, 이 멤버를 선언한 클래스의 하위 클래스에서도 접근할 수 있다.
- public : 모든 곳에서 접근할 수 있다.

멤버의 접근성을 좁히지 못하게 방해하는 제약이 하나 있다. 상위 클래스에서 정의한 메서드를 하위 클래스에서 재정의 할 때 그 접근 수준을 상위 클래스에서보다 좁게 설정 할 수 없다. 이를 어길시 컴파일 에러가 발생 할 것이다.

### public 클래스의 인스턴스 필드는 되도록 public이 아니어야 한다.

필드가 가변 객체를 참조하거나, final이 아닌 인스턴스 필드를 public으로 선언하면 그필드에 담을 수 있는 값을 제한할 힘을 잃게 된다.

게다가 pulibc 가변 필드를 갖는 클래스는 Thread safe 하지 않다. 심지어 final이면서 불변 객체를 참조하더라도 문제가 있다.

이러한 문제는 정적 필드에서도 마찬가지나 예외가 하나있다.

해당클래스가 표현하는 추상 개념을 완성하는 데 꼭 필요한 구성요소로써의 상수라면 public static final 필드로 공개해도 좋다. 관례상 대문자 알파벳과 이름이 길어지면 (_) 밑줄로 구분을 한다.

이런 필드는 반드시 기본 타입 값이나 불변 객체를 참조해야 한다. 가변 객체를 참조한다면 final이 아닌 필드에 적용 되는 불이익이 그대로 적용된다.

### 클래스에서 public static final 배열 필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공해서는 안 된다.

접근자를 제공한다면 클라이언트에서 이 필드를 수정할 수 있게 된다. 보안의 허점이 존재한다.

아래 코드를 보면 이해가 갈 것이다.

```java
@NoArgsConstructor
public class FinalArray {
    public static final int[] ARRAY = {1, 2, 3, 4, 5};

    public int[] getArr() {
        return ARRAY;
    }

    public void setArr(int num) {
        for (int i = 0; i < num; i++) {
           ARRAY[i] = i;
        }
    }
}
```

```java
@Test
@DisplayName("public static final int[] arr test")
void arrTest() {
    FinalArray finalArray = new FinalArray();
    int[] beforArr = finalArray.getArr();

    System.out.println(Arrays.toString(beforArr));

    finalArray.setArr(2);
    int[] afterArr = finalArray.getArr();

    System.out.println(Arrays.toString(afterArr));

    //[1, 2, 3, 4, 5]
    //[0, 1, 3, 4, 5]
}
```

위의 문제점의 해결책은 2가지다. 첫 번째 방법은 public 배열을 private으로 만들고 pulic 불변 리스트를 추가하는 것이다.

```java
public class FinalArraySolution {
    private static final int[]ARRAY= {1, 2, 3, 4, 5};
    public static final List<int []>ARRAYS=
            Collections.unmodifiableList(Arrays.asList(ARRAY));
}
```

두 번째 방법은 배열을 private으로 만들고 그 복사본을 반환하는 public 메서드를 추가하는 방법이다.

```java
public class FinalArraySolution2 {
    private static final int[]ARRAY= {1, 2, 3, 4, 5};

    public static final int[] getArray() {
        returnARRAY.clone();
    }
}
```

2가지 방법 중 어떤 것이 나은지 판단하여 선택하자. 어느 반환 타입이 더 쓰기 편할지, 성능은 어느 쪽이 나을지 고민해 정하자.

### 정리

- 접근성은 가능한 한 최소한으로 하라
- public 클래스는 상수용 public static final 필드 외에는 어떠한 public 필드를 가져서는 안된다.
- public static final 필드가 참조하는 객체가 불변인지 확인하라