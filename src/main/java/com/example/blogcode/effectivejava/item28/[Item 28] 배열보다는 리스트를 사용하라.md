# [Item 28] 배열보다는 리스트를 사용하라

## 배열과 제네릭 타입의 차이

### 1. 공변과 불공변

- 배열은 공변이다
    1. sub가 super의 하위 타입이라면 sub[]는 배열 super[]의 하위 타입이 된다.

```java
Object[] objectArray = new Long[1];
objectArray[0] = "어떤 타입이든 저장할 수 있는데.........."; // ArrayStoreException 던짐
```

배열은 런타임에도 자신이 담기로 한 원소의 타입을 인지하고 확인한다.

그래서 컴파일에는 아무런 영향을 끼치지 않지만 런타임 시 `ArrayStoreException`을 던진다.

- 제네릭은 불공변이다.
    1. 서로 다른 타입 type1과 type2가 있을 때 List<type1> 은 List<type2> 의 상위 타입도 아니고 하위 타입도 아니다.

```java
// 컴파일 시 예외
List<Object> list = new ArrayList<Long>();
list.add("컴파일에서 체크!"); // Compile Error!
```

컴파일러에게 타입을 명시해주어 컴파일 시 에러가 발생한다.

### 2. 실체화(reify)

- 배열은 런타임 시에도 타입의 정보를 가진다.
- 제네릭은 런타임에는 소거된다.

원소 타입을 컴파일 타임에만 검사하며 런타임에는 알수가 없다.

소거는 제네릭이 지원되기 전의 레거시 코드와 제네릭 타입을 함계 사용할 수 있게 해주는 메커니즘이다.

제네릭 배열을 만들지 못하게 막은 이유?

- 타입의 안정성을 보장하기 어려워
- 만약 허용한다면 컴파일러가 자동 생성한 형변환 코드에서 런타임에 ClassCastException을 던짐
    - 타입의 안정성을 보장하는 제네릭의 목표에 맞지 않는다.
    

예제 

```java
List<String>[] stringLists = new List<String>[1]; 
```

만약 제네릭과 배열 타입을 같이 사용하는 것을 허용하게 된다면?

```java
List<Integer> integers = List.of(1);
Object[] objects = stringLists;
objects[0] = integers;
```

먼저 원소가 하나인 List<Integer>를 생성하고 새로운 Object[] 타입에 stringLists를 할당한다. 배열은 공변인 특징을 가지고 있기에 성공한다.

다음으로 Object배열의 첫번째 인덱스에 List<Integer>를 할당한다. 제네릭은 소거 방식의 메커니즘이 존재하므로 이역시도 성공한다.

문제는 아래 코드이다.

```java
String s = stringLists[0].get(0);
```

List<String> 타입의 인스턴스만 받겠다는 stringLists 배열에는 List<Integer> 타입의 인스턴스가 저장되어 있다.

이것을 꺼내려 할 때 컴파일러는 String으로 형변환을 하게 되는데 저장되어 있는 인스턴스가 List<Integer> 타입이므로 ClassCastException을 던지게 될 것이다.

따라서 이런 경우를 방지하기위해 제네릭 배열을 제공하지 않는다.

### E, List<E>, List<String> 같은 타입을 실체화 불가 타입이라한다

실체화 되지 않아 런타임 시에 컴파일타임 보다 타입 정보를 적게 가지는 타입이다.

### 제네릭 타입과 가변인수 메서드를 함께 사용하면 해석하기 어려운 경고 메시지를 받게 된다.

가변인수 메서드를 호출할 때마다 가변인수 매개변수를 담을 배열이 하나 만들어지는데, 이때 그 배열의 원소가 실체화 불가 타입이라면 경고가 발생한다. 

이를 @SafeVarargs 애너테이션으로 대처할 수 있다.

배열로 형변환할 때 제네릭 배열 생성 오류나 비검사 형변환 경고가 뜨는 경우 배열타입 대신 제네릭 컬렉션을 사용하면 해결된다. 조금 복잡해지고 성능이 나빠질 수 있지만 타입 안정성과 상호운용성은 좋아진다.

### 예제 코드

```java
public class Chooser {
    private final Object[] choiceArray;

    public Chooser(Collection choices) {
        this.choiceArray = choices.toArray();
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray[random.nextInt(choiceArray.length)]
    }
}
```

생성자에서 컬레션을 받는 Chooser 클래스다.

컬렉션 안의 원소 중 하나를 무작위로 선택해 반환하는 choose 메서드를 제공하는데 choose 메서드를 호출할 때마다 Object를 원하는 타입으로 변환해야 한다.

타입이 다른 원소가 들어 있다면 런타임에 형변환 오류가 날 것이다.

### 제네릭 수정

```java
public class Chooser<T> {
    private final T[] choiceArray;

    public Chooser(Collection<T> choices) {
        this.choiceArray = choices.toArray(); // 컴파일 에러
    }

/// choose 메서드는 그대로
}
```

제네릭을 도입하여 수정하였다. 하지만 컴파일에서 에러를 내주었다. choies.toArray() 의 반환타입이 Object[]이므로 T[] 타입으로 캐스팅하면 된다.

```java
public Chooser(Collection<T> choices) {
        this.choiceArray = (T[]) choices.toArray(); // 컴파일 경고 warinng!!
}
```

수정해서 확인해보니 컴파일 경고가 발생한다.

이는 컴파일러가 T에 대한 타입을 알고 있지 않아 경고가 발생한 것이다. 이 형변환은 런타임에도 안전하지 못하다는 경고 메시지다.

단순히 경고 메시지다. 만약 개발자가 타입이 안전하다고 확신한다면 주석을 남기고 애너테이션을 달아 경고를 숨겨도 된다.

하지만 경고를 제거하는 것이 더 낫기에 배열보다 리스트를 사용하여 경고를 없애자.

```java
public class Chooser<T> {
    private final List<T> choiceArray;

    public Chooser(Collection<T> choices) {
        this.choiceArray = new ArrayList<>(choices);
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray.get(random.nextInt(choiceArray.size()));
    }
}
```