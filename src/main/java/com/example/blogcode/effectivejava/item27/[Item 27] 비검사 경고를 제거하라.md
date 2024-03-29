# [Item 27] 비검사 경고를 제거하라

제네릭을 사용하기 시작하면 수많은 컴파일러 경고들을 마주치게 되는데 이러한 경고들은 가능한 많이 제거하는 것이 좋다.

경고들을 모두 제거 한다면, 그 코드는 타입의 안정성이 보장된다.

비검사 경고는 쉽게 제거할 수 있다.

```java
List<String> list = new ArrayList();
```

컴파일 경고가 나오는 코드다.

```java
List<String> list = new ArrayList<>();
```

제네릭 타입으로 인스턴스를 생성하고 뒤에 다이아몬드 연산자를 생성해야 한다.

### 경고를 제거할 수 없지만 타입이 안전하다고 보장되면 경고를 숨겨라

@SuppressWarnings("unchecked")으로 경고를 숨기자.

단, 타입 안정성을 검증하지 않고 경고를 숨기면 절대 안된다. 해당 코드는 경고 없이 컴파일되지만, 런타임 시 여전히 ClassCastException을 던질 수 있다.

### @SuppressWarnings 에노테이션 주의사항

1. 가능한 좁은 범위로 적용

해당 에노테이션은 선언 범위가 넓기에 최대한 좁은 범위 즉, 변수나 메서드 생성자 등에 선언 해야 한다.

리턴문에는 선언 할 수 없어 지역 변수를 생성하여 선언하면 된다.

범위를 크게 잡게 되면 놓치는 부분이 있을 수 있으니 전체 범위에 선언하지마라.

1. 항상 주석을 남겨라

코드의 경고를 무시해도 안전한 이유를 항상 주석으로 남겨야 한다.

다른 사람이 코드를 이해하는데 도움이 되며 다른 사람이 그 코드를 잘못 수정하여 타입 안정성을 잃는 상황을 줄여준다.