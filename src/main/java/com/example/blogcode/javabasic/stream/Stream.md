# Stream

배열이나 컬렉션들 자료들의 대해 연산을 해준다. 연산들의 처리가 추상화 되어 있고 일관성 있게 제공해주는 기능이다.

- 스트림은 한번 생성하고 사용한 스트림은 소모가 되므로 재사용할 수 없다는 단점이 있다.
- 스트림 연산은 기존 자료를 변경하지 않는다.
- 스트림 연산은 중간 연산과 최종 연산이 있다.
    - 중간 연산은 1 ~ n 번 연산 가능
    - 최종 연산은 중간 연산이 된 후 에 1 번 연산 가능
    - 중간 연산 중에 결과는 연산 중에 알 수 가 없다. 최종 연산이 되어야 결과를 알 수 있다
        - 이를 `“지연 연산"` 이라 한다.

### 기본적인 최종 연산을 알아보자.

```java
				int[] arr = {1, 2, 3, 4, 5};

        int sum = Arrays.stream(arr).sum();
        int count = (int) Arrays.stream(arr).count();
        int maxValue = Arrays.stream(arr).max().orElseThrow(); 
        int minValue = Arrays.stream(arr).min().orElseThrow();
```

- 자료들의 합을 구하는 sum()
- 자료들의 갯수를 구하는 count()
- 자료 중에 가장 큰 수를 구하는 max()
- 자료 중에 가장 작은 수를 구하는 min()

대표적인 연산인데 max 나 min 같은 경우 반환 타입이 Optional 이여서 orElseThrow()를 사용해야한다.

forEach문 도 사용할 수 있다.

```java
Arrays.*stream*(arr).forEach(value -> System.*out*.println(value));
```

forEach() 연산으로 각각의 자료를 순회 한다. 여기서 람다식을 사용하는데 위와 같이 작성할 것이다.

여기서 메서드 참조법을 사용하면 더욱 더 간결해 질 수 있다. 메서드 참조는 매개 변수와 실행문에 들어가는 매개 변수의 갯수와 타입이 일치하면 사용 할 수 있는데 아래와 같이 작성할 수 있다.

```java
Arrays.stream(arr).forEach(System.out::println);
```

해당 클래스 :: 메서드() 로 매개 변수를 생략 할 수 있고 간결하게 사용할 수 있다.

### 중간 연산

- 배열의 숫자 중 특정 숫자보다 큰 숫자들만 뽑아 내보자.

```java
Arrays.stream(arr).filter(value -> value > 2).forEach(System.out::println);
```

- 자료들의 정렬이 필요할 때도 활용이 가능하다.

```java
int[] array = Arrays.stream(arr).sorted().toArray();
```

기본적인 정렬은 오름차순 정렬이다.

- 자료의 타입도 변경이 가능하다.

int 타입의 배열을 Wrapper 클래스인 Integer로 Boxing 할 수 도 있다.

```java
Integer[] integers = Arrays.stream(arr).boxed().toArray(value -> {
    Integer[] intArr = new Integer[arr.length];
    for (int i = 0; i < arr.length; i++) {
        intArr[i] = value;
    }
    return intArr;
});
```

위의 코드에서 간결하게..

```java
Integer[] integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);
```

- 중복된 원소를 제거할 수 있다.

```java
Arrays.stream(arr).distinct().toArray();
```

### 최종 연산

- 연산 수행에 대한 구현도 할 수 있다.
- reduce()

간단하게 가장 큰 수를 얻는 연산을 구현하겠습니다.

```java
int minValue = Arrays.stream(arr).reduce(0, (left, right) -> {
    if (left > right) return left;
    else return right;
});
```

첫 번째 인자는 초기값을 지정 할 수 있게 하는 인자이고, 두 번째는 연산을 진행 할 인자입니다.

### Function 라이브러리 활용

- **Function<T, R> : 매개 타입과 반환 타입이 있는 경우 → input / output**
- **Supplier<T> : 반환 타입만 있는 경우 → no / output**
- **Consumer<T> : 매개 변수 타입만 있는 경우 → input / no**
- **Predicate<T> : 매개 변수 타입이 있고 반환 타입이 boolean 인 경우 → input → output(boolean)**
- **Runnable : 매개 변수 타입과 반환 타입이 없는 경우 → no / no**
- **BiFunction : 2개의 매개 변수와 한 개의 반환 값이 존재하는 경우 → input 2 / output**
- **BiConsumer : 2개의 매개 변수만 있고 반환 값이 없는 경우 → input 2 / no**
- **Bipredicate : 2개의 매개 변수가 있고 반환 값이 boolean 인 경우 -? input 2 / output(boolean)**
- **UnaryOperator : 한개의 매개 변수 타입과 한개의 반환 타입이 일치 해야하는 경우 → input / output 타입 일치**
- **BinaryOperator : 2개의 매개 변수 타입과 한개의 반환 타입이 일치 해야하는 경우 → input 2 / output 타입 일치**