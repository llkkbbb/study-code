# Lambda expression

자바의 기능이 늘어남으로 심플성과 안정성을 추구하는 자바는 jdk8 부터 람다식(함수형 프로그래밍 방식)의 스펙을 제공해준다.

매개변수를 받고 받은 매개변수를 기반으로 내부에서만 함수가 진행 되기 때문에 외부 자료에 부수적인 영향을 주지 않는 장점이 있다. 즉, 내부적인 작업이 가능하기 때문에 병렬 처리가 가능하고 멀티 스레드 환경에서 안정성의 장점을 가진다. 하지만 가독성이 떨어지는 단점이 존재하긴 한다.

장점

- 외부 자료에 영향을 주지 않고 내부 작업이 가능
- 병렬 처리가 가능
- 멀티 스레드 환경에서 안정성

단점

- 가독성이 떨어짐

### 람다식 문법

- (매개변수) → (실행문)
- 두 수를 입력 받아 sum 하는 sum()

funtional interface 사용

```java
@FunctionalInterface
public interface Function {

    public int sum(List<Integer> list);
}
```

@FunctionalInterface 에노테이션은 해당 인터페이스에 함수가 한개인 함수형 인터페이스를 정의한다고 컴파일러에게 알려주는 역할을 한다. 만약 함수가 2개 이상이라면 컴파일러는 컴파일 에러를 낼 것이다.

```java
List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

Function function = list1 -> list.stream().mapToInt(Integer::intValue).sum();

System.out.println(function.sum(list)); // 55
```

1부터 10까지 담겨져 있는 리스트를 생성한다. 함수형 인터페이스를 사용해서 값을 구한다.

람다식은 내부적으로 익명 내부 클래스를 만들어 함수를 구현한다. 개발자가 인터페이스를 구현하여 클래스를 생성하는 단계를 생략할 수 있다.

### 람다식 표현에는 간결하게 표현할 수 있다.

- 매개변수가 한 개일 때만 () 생략 가능
- 실행문이 한 줄일 때만 {} 생략 가능
- 타입은 컴파일러가 자동 추론을 하므로 생략 가능
- 실행문에 return 문이 있을 시 생략 불가