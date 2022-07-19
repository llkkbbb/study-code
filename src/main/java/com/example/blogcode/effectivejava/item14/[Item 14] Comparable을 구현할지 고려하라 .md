# [Item 14] Comparable을 구현할지 고려하라

CompareTo는 동치성 비교에 순서까지 비교할 수 있으며 제네릭 하다. Comparable을 구현했다는 것은 해당 클래스의 순서가 있음을 뜻한다.

Arrays의 클래스는 Comparable을 구현하여 아래와  같이 손쉽게 정렬할 수 있다.

```java
Arrays.sort(arr);
```

검색, 극단값 계산, 자동 정렬되는 컬렉션 관리도 쉽게 할 수 있다. 

자바의 라이브러리의 모든 값 클래스와 열거타입이 Comparable을 구현했다. 알파벳, 숫자, 연대 같이 순서가 명확한 값 클래스를 작성한다면 반드시 Comparable을 구현하자.

### comparTo 메서드 규약

1. 이 객체와 주어진 객체의 순서를 비교한다.
2. 이 객체가 주어진 객체보다 작으면 음의 정수, 같으면 0, 크면 양의 정수를 반환한다.
3. 객체와 비교할 수 없는 타입의 객체가 주어진 경우 `ClassCastException`을 발생

아래 글의 sgn 은 수학의 부호 함수를 뜻한다. 표현식이 값이 음수, 0, 양수일 때 -1, 0, 1을 반환하도록 정희했다.

- Comparable을 구현한 클래스는 모든 x, y에 대해 sgn(x.compareTo(y)) == -sgn(y.compareTo(x))여야 한다.
    - sgn(x.compareTo(y))는 -sgn(y.compareTo(x) 가 예외를 던질 때에 예외를 던저야 한다.
- Comparable을 구현한 클래스는 추이성을 보장해야 한다.
- Comparable을 구현한 클래스는 모든 z에 대해 x.compareTo(y) == 0이면 sgn(x.compareTo(z)) == sgn(y.compareTo(z))다.
- (x.compareTo(y) == 0) == (x.equals(y)) 여야 한다. 이 권고를 지키지 않는 모든 클래스는 그 사실을 명시해야 한다.
    - 주의 : 이클래스의 순서는 equals 메서드와 일관되지 않는다.
    

compareTo 메서드는 equals 메서드와 달리 객체의 타입을 신경쓰지 않아도 된다. 타입이 맞지 않으면 ClassCastException을 던지면 된다. 비교를 허용할 땐 비교할 객체들이 구현한 공통 인턴페이스를 매개로 이루어진다.

### 규약을 자세히 살펴보자

- 첫 번째 규약은 두 객체 참조의 순서를 바꿔 비교해도 예상한 결과가 나와야 한다는 말이다. 즉, 첫 번째의 객체가 두 번째의 객체보다 작으면, 두 번째가 첫 번째 객체보다 커야 한다.

- 두 번째 규약은 첫 번째가 두 번째보다 크고 두 번째가 세 번째보다 크면, 첫 번째는 세 번째보다 커야 한다는 말이다.

- 세 번째 규약은 크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같아야 한다는 뜻이다.

이 세가지 규약을 보면 동치성 검사도 equals 규약과 똑같이 반사성, 대칭성, 추이성을 충족해야 함을 뜻한다.

### 주의 사항

Comparable을 구현한 클래스를 확장해 값 컴포넌트를 추가하고 싶다면, 확장하는 대신 독립된 클래스를 만들고, 이 클래스에 원래 클래스의 인스턴스를 가리키는 필드를 두자.

그런 다음 내부 인스턴스를 반환하는 ‘뷰’ 메서드를 제공하면 되고 바깥 클래스에 우리가 원하는 compareTo 메서드를 구현해넣을 수 있다.

- 마지막 규약은 compareTo 메서드로 수행한 동치성 테스트의 결과가 equals와 같아야 한다는 것이다.

### CompareTo 메서드 작성 요령

- comparable 인터페이스는 제네릭 타입을 받는 인터페이스다.
- 컴파일 시점에 타입이 정해지며 형변환을 따로 하지 않아도 된다.
- null 을 인수로 넣으면 NullPointException 을 던져야 한다.
- compareTo 메서드는 각 필드가 동치인지를 비교하는 것이 아니라 순서를 비교한다.
- 비교시에 comparTo 메서드를 재귀호출을 진행한다.

Comparable을 구현하지 않은 필드나 표준이 아닌 순서로 비교해야 한다면 비교자(Comparator)를 대신 사용한다.

### 객체 참조 필드가 하나뿐인 비교자

```java
public class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{
    @Override
    public int compareTo(CaseInsensitiveString cis) {
        return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
    }
...../// 나머지 코드 생략
}
```

CaseInsensitiveString이 Comparable<CaseInsensitiveString>을 구현한 것은 CaseInsensitiveString의 참조는 CaseInsensitiveString 참조와만 비교할 수 있다는 뜻으로 Comparable을 구현할 때 따르는 패턴이다.

정수 기본 타입 필드를 비교할 때 앞서 관계 연산자인 <, > 를 사용하기 보단 정적 메서드를 사용하라고 권장했다.

### 기본 타입 필드가 여럿일 때의 비교자

핵심 필드가 여럿일 때 가장 핵심적인 필드 부터 비교하자.

비교 결과가 0 이 아니라면 곧장 반환하자. 똑같다면 똑같지 않은 필드를 찾을 때까지 그다음으로 중요한 필드를 비교해나간다.

```java
@Override
public int compareTo(PhoneNumber pn) {
    int result = Integer.compare(firstNumber, pn.firstNumber); // 가장 중요
    if (result == 0) {
        result = Integer.compare(middleNumber, pn.middleNumber); // 두 번째 중요
        if (result == 0) {
            result = Integer.compare(lastNumber, pn.lastNumber); // 세 번째 중요
        }
    }
    return result;
}
```

### 비교자 생성 메서드를 활용한 비교자

자바 8에서 Comparator 인터페이스가 일련의 비교자 생성 메서드를 제공하여 비교자를 생성할 수 있게 되었다.

약간의 성능 저하가 뒤따르지만 코드가 훨씬 깔끔해진다.

```java
private static final Comparator<PhoneNumber>COMPARATOR=
        Comparator.comparingInt((PhoneNumber pn) -> pn.firstNumber)
                .thenComparingInt(pn -> pn.middleNumber)
                .thenComparingInt(pn -> pn.lastNumber);

@Override
public int compareTo(PhoneNumber pn) {
    returnCOMPARATOR.compare(this, pn);
}
```

### 해시코드 값의 차를 기준으로 하는 비교자 - 추이성 위배

```java
static Comparator<Object>hashCodeOrder= new Comparator<Object>() {
    @Override
    public int compare(Object o1, Object o2) {
        return o1.hashCode() - o2.hashCode();
    }
};
```

이 방식은 사용하면 안되요! 정수 오버플로를 일으키거나 부동소수점 계산 방식에 따른 오류를 낼 수 있어요!

위의 구현한 코드보다 빠르지도 않아요!

아래와 같은 코드를 사용하자!

### 정적 compare 메서드를 활용한 비교자

```java
static Comparator<Object>hashCodeOrder= new Comparator<Object>() {
    @Override
    public int compare(Object o1, Object o2) {
        return Integer.compare(o1.hashCode(), o2.hashCode());
    }
};
```

### 비교자 생성 메서드를 활용한 비교자

```java
static Comparator<Object> hashCodeOrder = Comparator.comparingInt(Object::hashCode);
```

### 정리

- 순서를 고려해야 하는 값 클래스를 작성한다면 꼭 Comparable 인터페이스를 구현하여 정렬, 검색, 비교 기능을 제공해야한다.
- 비교할 때 < > 관계 연산자를 사용하지 말고 정적 메서드를 사용하거나 Compartor 인터페이스가 제공하는 비교자 생성 메서드를 사용해야 한다.