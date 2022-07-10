# [ITEM 11] equals를 재정의하려거든 hashCode도 재정의하라

equals를 재정의한 클래스 모두에서 hashCode도 재정의해야 한다. 그렇지 않으면 equals 재정의 규약을 어기게 되어 문제를 일으킨다.

### 규약

- equals 비교에서 사용되는 정보가 변경되지 않았다면, 애플리케이션이 실행되는 동안 그 객체의 hashCode 메서드는 몇 번을 호출해도 일관되게 항상 같은 값을 반환해야 한다.
- equals 메서드가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 똑같은 값을 반환해야 한다.
- equals 메서드가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른 값을 반환할 필요는 없다. 단, 다른 객체에 대해서는 다른 값을 반환해야 해시테이블의 성능이 좋아진다.

hashCode 재정의를 잘못했을 때 크게 문제가 되는것은 ‘논리적으로 같은 객체는 같은 해시코드를 반환해야 한다’ 

모든 객체에게 `똑같은 해쉬 코드 값`을 주면 모든 객체가 해시테이블의 버킷 하나에 담겨 연결 리스트 처럼 동작 된다. 그 결과 `평균 수행 시간이 O(1)인 해시테이블이 O(n)`으로 느려져 성능이 영향을 미친다.

좋은 해시 함수라면 서로 다른 인스턴스에 다른 해시코드를 반환한다.

### 좋은 hashCode를 작성하는 요령

1. int 변수 result를 선언한 후 값 c로 초기화
    1. 값 c sms 해당 객체의 첫번째 핵심 필드의 해쉬 코드를 계산한 값이다.
2. 해당 객체의 나머지 핵심 필드 f 각각에 대해 다음 작업을 수행
    1. 해당 필드의 해시코드 c를 계산
        1. 기본 타입(primitive) 필드 → Type.hashCode(f)를 수행 → 해당 기본 타입의 박싱 클래스다.
        2. 참조 타입 필드면서 이 클래스의 equals 메서드가 이 필드의 equals 를 재귀적으로 호출해 비교한다면, 이 필드의 hashCode를 재귀적으로 호출한다.
        3. 필드가 배열이라면, 핵심 원소 각각을 별로의 필드처럼 다루어 재귀적으로 적용해 각 원소의 해시코드를 계산한다. 모든 원소가 핵심 원소라면 Arrays.hashCode를 사용한다.
    2. 단계 2.a 에서 계산한 해시코드 c로 result를 갱신한다.
        
        ex) result = 31 * result + c;
        
3. result를 반환한다.

### 주의할 점

- 해시코드 계산이 복잡해질 것 같으면, 피들의 표준형을 만들어 그 표준형의 hashCode를 호출한다.
- 필드의 값이 null이면 0을 사용한다.
- 파생 필드는 해시코드 계산에서 제외해도 된다.
- equals 비교에 사용되지 않은 필드는 `반드시` 제외해야 한다.
- 31을 곱하는 이유는 홀수이면서 소수이기 때문이다. 만약 곱할 숫자가 짝수이고 오버플로우가 발생된다면 정보를 잃게 된다. 2를 곱하는 경우는 시프트 연산과 같은 결과를 내기 때문이다.

### 전형적인 hashCode 메서드

```java
@Override
public int hashCode() {
    int result = Integer.hashCode(firstNumber);
    result = 31 * result + Integer.hashCode(middleNumber);
    result = 31 * result + Integer.hashCode(lastNumber);
    return result;
}
```

동치인 PhoneNumber 인스턴스들은 같은 해시코드를 가질 것이다.

### Objects.hash() 메서드

입력 변수를 담기 위한 배열이 만들어지고, 입력 중 박싱 언박싱 과정도 거쳐야 하기 때문에 성능상 조금 느리다.

```java
@Override
public int hashCode() {
    return Objects.hash(firstNumber, middleNumber, lastNumber);
}
```

### 지연 초기화 하는 hashCode 메서드

클래스가 불변이고 해시코드를 계산하는 비용이 크다면, 매번 새로 계산하기 보다는 캐싱하는 방식을 고려하자.

- 해당 타입의 객체가 키로 사용 될 것 같으면 인스턴스가 만들어질 때 해시코드를 계산해둬야 한다.

**해시의 키로 사용되지 않는 경우라면 hashCode가 처음 불릴 때 계산하는 지연 초기화 전략은 어떨까?**

- 필드를 지연 초기화하려면 크 클래스를 스레드 세이프하게 만들도록 신경 써야 한다.

예시 코드

```java
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
```

### 주의

- 성능을 높인다고 해시코드를 계산할 때 핵심 필드를 생략해서는 안된다.
- hashCode가 반환하는 값의 생성 규칙을 자세히 공표하지말자, 그래야 클라이언트가 이값에 의지하지 않게 되고 추후에 계산 방식을 바 꿀 수 있다.

equasl 메서드를 재정의할 때는 hashCode 메서드도 같이 재정의 해야한다. hashCode 정의도 규약에 따라야하고 서로 다른 인스턴스라면 되도록 해시코드도 서로 다르게 구현해야 한다.

하지만 현재 재정의하는데 도움을 주는 라이브러리와 IDE가 똑똑하니 구현한다고 스트레스 받지말자!