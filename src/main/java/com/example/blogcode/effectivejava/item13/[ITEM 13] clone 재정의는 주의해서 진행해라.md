# [ITEM 13] clone 재정의는 주의해서 진행해라

### Cloneable 인터페이스

Cloneable은 복제해도 되는 클래스임을 명시하는 용도의 인터페이스다.

```java
public class Person implements Cloneable {
//.....
}
```

아쉽게도 Cloneable 인터페이스는 의도한 목적을 제대로 이루지 못했다. 왜냐하면 Cloneable 인터페이스에는 아무런 정의가 되지 않은 빈통 인터페이스다.

```java
public interface Cloneable {
}
```

clone 메서드가 정의 된 곳은 `Object 클래스에 protected로 정의` 되어 있다.

```java
@HotSpotIntrinsicCandidate
protected native Object clone() throws CloneNotSupportedException;
```

### 그러면 Cloneable 인터페이스는 무슨 역할을 할까?

- Cloneable의 역할은 Object의 protected 메서드인 clone의 동작 방식을 결정한다.
- Cloneable을 구현한 클래스의 인스턴스에서 clone을 호출하면 그 객체의 필드들을 하나하나 복사한 객체를 반환하며
- 그렇지 않은 클래스의 인스턴스에서 호출하면 CloneNotSupportedException을 던진다.
    - 해당 인터페이스는 상당히 이례적으로 사용한 예이니 따라 하지는 말자.
    

### clone() 의 일반 규약

어떤 객체 x에 대해 다음 식은 참이다.

- x.clone() != x
- x.clone().getClass() == x.getClass()
    
    이 이상의 요구를 반드시 만족해야 하는 것은 아니다.
    
    아래 식도 참이지만, 필수는 아니다.
    
- x.clone().equals(x)
    
    
    관례상,  이 메서드가 반환하는 객체는 super.clone을 호출해 얻어야 한다. 해당 클래스와 모든 상위 클래스가 이 관례를 따른다면 다음 식은 참이다.
    
- x.clone().getClass() == x.getClass()
    
    관례상, 반환된 객체와 원본 객체는 독립적이어야 한다. 이를 만족하려면 super.clone으로 얻은 객체의 필드 중 하나 이상을 반환 전에 수정해야 할 수도 있다.
    

### 가변 상태를 참조하지 않는 클래스용 clone 메서드

불변 클래스는 굳이 clone 메서드를 제공하지 않는 게 좋다.

```java
@Override
public Person clone() {
    try {
        return (Person) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

1. 메서드가 동작하기 위해 클래스에 Cloneable이 구현 되어야 한다.
2. 공변 반환 타이핑을 지원하니 Person 타입으로 클라이언트가 형변환을 하지 않아도 되게끔 해주었다.
3. try-catch로 예외 처리를 한 이유는 clone 메서드에서 검사 예외인 CloneNotSupportedException을 던지도록 했기 때문이다. 하지만 Cloneable 인터페이스를 구현했으므로 super.clone() 이 성공할 것임을 안다.
    
    사실은 CloneNotSupportedException 예외는 비검사 예외였어야 한다.
    

### 가변상태를 참조하는 클래스용 clone 메서드

스택 클래스를 살펴봅시다.

```java
public class Stack {

    private Object[] elements;
    private int size = 0;
    private static final intDEFAULT_INITIAL_CAPACITY= 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
// ......
}
```

가변 객체인 배열을 참조하고 있는 클래스를 복제할 수 있도록 만들어보자.

단순히 super.clone()을 호출하는 식으로 구현 하면 필드 값들은 어떻게 될까?

```java
@Override
public Stack clone() {
    try {
        return (Stack) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

기본 값의 필드들은 값을 갖겠지만 elements 필드는 원본과 같은 배열을 참조할 것이다.

```java
@Test
@DisplayName("stack 객체 복제 후 elements 배열은 서로 달라야 하는데 같은 참조값의 주소를 가르키니 서로 같은 배열이다.")
void StackTest() throws CloneNotSupportedException {
    Stack stack = new Stack();
    Stack cloneStack = stack.clone();

    assertEquals(stack.getElements(), cloneStack.getElements());
}
```

따라서 원본과 복제본 중 하나의 elements 필드를 변경하면 다른 하나도 변경이 되어 불변식이 성립되지 않는다.

위의 문제점을 보안하는 clone메서드

```java
@Override
public Stack clone() {
    try {
        Stack result = (Stack) super.clone();
        result.elements = elements.clone();
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

- elements 배열의 clone을 재귀적으로 호출해준다.
- 배열을 복제할 때 clone() 을 권장한다.

한편, elements 필드가 final 이라면 위의 메서드는 소용이 없다.

### 복잡한 가변 상태를 갖는 클래스용 재귀적 clone 메서드

엔트리를 갖는 해시테이블을 생각해보자. 해시테이블 내부는 버킷들의 배열이고, 각 버킷은 키와 값의 구조를 담는 연결 리스트의 첫 번째 엔트리를 참조한다.

```java
public class HashTable implements Cloneable {

    private Entry[] buckets;

    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
//    ......
}
```

stack 에서 처럼 단순히 버킷 배열의 clone을 재귀적으로 호출해보자,

```java
@Override
public HashTable clone() {
    try {
        HashTable result = (HashTable) super.clone();
        result.buckets = buckets.clone();
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

복제본은 자신만의 버킷 배열을 갖지만, 이 배열의 원본과 같은 연결 리스트를 참조하여 원본과 복제본 모두 예기치 않게 동작할 가능성이 생긴다.

이를 해결 하려면 각 버킷을 구성하는 연결 리스트를 복사해야 한다.

```java
// 이 엔트리가 가리키는 연결 리스트를 재귀적으로 복사
Entry deepCopy() {
    return new Entry(key, value, next == null ? null : next.deepCopy());
}
```

```java
@Override
public HashTable clone() {
    try {
        HashTable result = (HashTable) super.clone();
        result.buckets = new Entry[buckets.length];
        for (int i = 0; i < buckets.length; i++)
            if (buckets[i] != null)
                result.buckets[i] = buckets[i].deepCopy();
        return result;
    } catch (CloneNotSupportedException e) {
        throw new AssertionError();
    }
}
```

깊은복사(deep copy)를 지원하도록 보강한 메서드이다. 적절한 크기의 새로운 버킷 배열을 할당하고 할당 받은 배열에 원본의 버킷 배열을 순회하며 깊은 복사를 수행한다.

하지만 위의 deepCopy() 메서드는 연결 되어 있는 엔트리가 많으면 많은 재귀 호출 때문에 스택 프레임을 소비하여 스택 오버플로를 일으킬 위험이 있다.

이 문제를 해결 하려면 재귀 호출 대신 반복자를 써서 순회하는 방향으로 수정해야 한다.

### 엔트리 자신이 가리키는 연결 리스트를 반복적으로 복사한다.

```java
Entry deepCopy() {
    Entry result = new Entry(key, value, next);
    for (Entry p = result; p.next != null; p = p.next) {
        p.next = new Entry(p.next.key, p.next.value, p.next.next);
    }
    return result;
}
```

### Clone 메서드 사용 팁

- 사용하기 편하게 public 인 clone 메서드에서 throws 절을 없앤다.
- 상속용 클래스는 Cloneable을 구현해서는 안된다.
    - clone 메서드를 재정의 하여 CloneNotSupportedException을 던져라
- 지원하지 못하게 final 로 정의
    
    ```java
    @Override
    protected final Object clone() throws CloneNotSupportedException {
        return super.clone();
    ```
    
- clone 메서드는 동기화를 신경 쓰지 않아서 clone을 재정의 후에 `동기화`를 해줘야 한다.

### 꼭 clone 메서드가 필요한가?

Cloneable을 이미 구현한 클래스를 확장한다면 어쩔 수 없이 clone을 잘 작동 하도록 구현해야 한다.

그렇지 않으면 `복사 생성자`와 `복사 팩터리`라는 더 나은 객체 복사 방식을 제공할 수 있다.

복사 생성자

```java
public Person(Person person) {
    this.name = person.getName();
    this.age = person.getAge();
}
```

복사 팩터리

```java
public static Person newInstance(Person person) {
    return new Person(person);
}
```

이 방법들의 장점

- 일반 규약에 얽매이지 않고 생성자를 쓰지 않는 방식을 사용하지 않는다
- 정상적인 final 필드 용법과도 충돌하지 않는다
- 불필요한 검사 예외를 던지지 않고, 형변환도 필요하지 않다.
- 원본의 구현 타입에 얽매이지 않고 복제본의 타입을 직접 선택할 수 있다.

### 정리

- Cloneable을 확장해서는 안되며, 새로운 클래스도 이를 구현해서는 안된다.
- final 클래스일 때 성능 최적화 관점에서 검토 후에 문제가 없을 때만 허용해라.
- 복제 기능은 생성자와 팩터리를 이용하는 것이 최고다
- 배열은 clone() 을 사용하자.