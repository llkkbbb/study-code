# [Item 29] 이왕이면 제네릭 타입으로 만들라

책에는 Stack 클래스를 예로 들었는데 이 글은 stack클래스를 모방한 Cage 클래스로 예를 들겠습니다.

```java
public class Cage {
    private Object[] cages;
    private int size;
    private static intDEFAULT_SIZE= 10;

    public Cage() {
        this.cages = new Object[DEFAULT_SIZE];
    }

    public void push(Object o) {
        checkCapacity();
        this.cages[size++] = o;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = cages[--size];
        cages[size] = null;
        return result;
    }

    private void checkCapacity() {
        if (cages.length == size) {
            this.cages = Arrays.copyOf(this.cages, 2 * size + 1);
        }
    }
}
```

Object 타입의 원소들을 저장하려는 Cage 클래스다. 이 클래스는 제네릭 타입이여야 마땅한 클래스다.

오히려 cage 안에서 객체를 꺼내 형변환 과정에서 런타임 에러가 날 위험이 있다.

제네릭 타입으로 수정해보자.

```java
public class GenericCage<E> {
    private E[] cages;
    private int size;
    private static intDEFAULT_SIZE= 10;

    public GenericCage() {
        this.cages = new E[DEFAULT_SIZE];
    }

    public void push(E o) {
        checkCapacity();
        this.cages[size++] = o;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = cages[--size];
        cages[size] = null;
        return result;
    }

    private void checkCapacity() {
        if (cages.length == size) {
            this.cages = Arrays.copyOf(this.cages, 2 * size + 1);
        }
    }
}
```

Object 타입을 E 타입으로 수정하였다.

컴파일에서  this.cages = new E[DEFAULT_SIZE]; 이부분이 에러가 난다. 앞서 말했듯이 제네릭으로 만들려할 때의 실체화가 문제가 된다.

### 해결책을 해결책은 두 가지다.

- 첫 번째.

제네릭 배열 생성을 금지하는 제약을 대놓고 우회하는 방법

```java
public GenericCage() {
    this.cages = (E[]) new Object[DEFAULT_SIZE]; // 컴파일 경고
}
```

컴파일 경고가 발생하며 이는 타입 안정을 보장하지 않는다는 메시지다.

하지만 push() 메서드를 보면 항상 E 타입의 원소만 E타입의 배열에 저장하게 된다. 이러한 경우는 타입이 안전하다고 판단할 수 있어 @SuppressWarnings 애너테이션으로 범위를 최소화하여 경고를 숨긴다.

```java
@SuppressWarnings("unchecked")
public GenericCage() {
    this.cages = (E[]) new Object[DEFAULT_SIZE];
}
```

cages 필드의 타입이 E[] 타입으로 받는 것으로 가독성이 좋아지며 코드도 간결하다.

하지만 E 가 Object 가 아니면 런타임 타입이 컴파일타임 타입과 달라 `힙 오염`이 발생한다.

- 두 번째

cages 타입을 E[] 에서 Object[] 로 바꾸는 것이다.

```java
private Object[] cages;

public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result =  cages[--size]; // 컴파일 에러
        cages[size] = null;
        return result;
    }
```

Object 타입으로 변경하면 pop() 메서드에서 원소를 꺼내오는 부분에서 컴파일 에러가 발생한다.

이는 Object 타입에서 원소를 형변환하는데 문제가 되어서 나오는 에러인데 받는 타입이 E 타입이므로 E 타입으로 형변환을 해준다.

```java
E result = (E) cages[--size]; // 컴파일 경고
```

변경 후 코드를 보면 컴파일 경고가 발생한다. 이 부분을 @SuppressWarnings 애노테이션으로 경고를 숨겨주자.

```java
@SuppressWarnings("unchecked") E result = (E) cages[--size];
```

이 방식은 꺼내올 때 마다 E 타입으로 형변환을 해야하는 단점이 존재한다.

### 제네릭 타입 매개변수 제약

```java
List<String> -> o
List<List<String>> -> o
List<int> - x
```

래퍼 타입은 가능하나 프리미티브 타입으로 제네릭을 만들 수 없다.

### 한정적 타입 매개변수

```java
List<E extends Person>
```

Person 타입과 Person 타입의 하위 타입만 받는 다는 뜻이다.