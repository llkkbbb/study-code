# [Item 18] 상속보다는 컴포지션을 사용하라

코드를 재사용하는데 상속은 강력한 수단이다. 하지만 항상 재사용하는데에 있어 좋은 것은 아니다.

같은 패키지 내에서 상위 클래스와 하위 클래스를 모두 통제한다면 좋지만 다른 패키지 내에서 상속을 한다면 관리가 쉽지 않을 것이다.

### 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다.

상위 클래스를 확장을 고려하지 않고 설계하면 하위 클래스에서 에러가 날 수 있기에 충분히 고려해서 설계하자.

HashSet을 잘못 상속한 코드 예

```java
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentedHashSet<E> extends HashSet<E> {

    private int addCount = 0;

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
```

addAll() 메서드로 원소를 3개 추가해서 addCount를 반환해보면 예상되는 반환 수는 3이다. 하지만 실제로는 6이 나올 것 이다.

```java
@Test
void InstrumentedHashSetTest() {
    class Item {
        private String name;

        public Item(String name) {
            this.name = name;
        }
    }
    
    InstrumentedHashSet<Item> hashSet = new InstrumentedHashSet<>();
    
    hashSet.addAll(List.of(new Item("안경"), new Item("옷"), new Item("모자")));
    
    assertThat(hashSet.getAddCount()).isEqualTo(3);
}
```

```java
// 결과
org.opentest4j.AssertionFailedError: 
expected: 3
 but was: 6
Expected :3
Actual   :6
```

이러한 상황이 왜 발생하는 걸까?

addAll() 메서드는 add() 메서드를 호출하여 내부에서 구현된 메서드다. 따라서 addAll()를 사용한 메서드는 add() 메서드를 중복해서 사용하여 count가 중복되어 증가된다. 

addAll() 메서드를 다른식으로 재정의할 수 도 있다.

```java
@Override
public boolean addAll(Collection<? extends E> c) {
    boolean flag = false;
    for (E e : c) {
        add(e);
        flag = true;
    }
    return flag;
}
```

매개변수로 받은 컬렉션을 순회하며 원소 하나당 add를 해주는 로직이다. 이 방법은 HashSet의 addAll()을 호출하여 사용하는 것 보단 나을 것이다.

여전히 상위 클래스의 메서드를 재정의 하는 방식은 어렵고, 시간이 들며, 에러가 날 수도 있거나 성능을 오히려 떨어뜨릴 수도 있는 문제점이 있고, 재정의하는 메서드의 접근제어자를 private으로 바꾸면 컴파일 에러가 난다.

위의 문제점 모두가 오버라이딩시에 문제가 된다. 그러면 오버로딩을 해서 구현하면 어떨까? 위험이 전혀 없는 것은 아니다. 오버로딩의 규칙이 있기에 규칙에 맞지 않으면 컴파일 에러가 난다.

이 문제점을 피해가는 묘안이 있다. private 필드로 인스턴스를 참조하는 것이다. 기존 클래스가 새로운 클래스의 구성요소가 된다는 것을 컴포지션이라 한다. 새 클래스에서 인스턴스 메서드들은 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환한다. 이 방식을 포워딩이라 한다.

위의 컴포지션과 포워딩 방식으로 기존 클래스의 내부 구현 방식의 영향에서 벗어나며, 기존 클래스의 새로운 메서드가 추가되더라도 영향을 받지 않는다.

### 상속 대신 컴포지션을 사용

래퍼 클래스

```java
public class InstrumentedSet<E> extends ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
}
```

재사용할 수 있는 전달 클래스

```java
public class ForwardingSet<E> implements Set<E> {
    private final Set<E> s;
    public ForwardingSet(Set<E> s) {
        this.s = s;
    }

    @Override
    public boolean add(E e) {
        return s.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return s.addAll(c);
    }

    @Override
    public int size() {
        return s.size();
    }
}
```

위에서 구현한 코드는 HashSet 의 기능을 정의하여 유연하고 견고하게 설계되었다.

어떠한 Set구현체라도 생성이 가능하다.

```java
Set<Integer> integers1 = new InstrumentedSet<>(new TreeSet<>());
Set<Integer> integers2 = new InstrumentedSet<>(new HashSet<>());
```

InstrumentedSet와 같은 클래스를 래퍼 클래스라고 하며 데코레이터 패턴이라고도 한다.

래퍼 클래스의 단점은 콜백과는 어울리지 않는다. 콜백은 자기 자신의 참조를 다른 객체에 넘겨서 다음 호출할 때 사용하도록 하는데 InstrumentedSet의 내부 객체는 자신을 감싸고 있는 래퍼의 존재를 모르니 대신 자신(this)의 참조를 넘기고, 콜백 때는 래퍼가 아닌 내부 객체를 호출하게 된다. 이를 `self 문제`라 한다.

따라서 상속은 반드시 하위 클래스가 상위 클래스의 진짜 하위 타입인 상황에서만 쓰여야 한다.

다르게 말하면, 클래스 B가 클래스 A와 is-a 관계일 떄만 클래스 A를 상속해야 한다.

### 정리

- 상속은 강력하지만 캡슐화를 해친다.
- 상위 클래스와 하위 클래스가 순수한 is-a 관계일 때만 써야 한다.
- 상위 클래스가 확장을 고려하여 설계하지 않았다면 문제가 된다.
- 상속의 취약점을 피하려면 컴포지션과 전달을 사용하자.
    - 래퍼 클래스는 하위 클래스보다 견고하고 강력하다.