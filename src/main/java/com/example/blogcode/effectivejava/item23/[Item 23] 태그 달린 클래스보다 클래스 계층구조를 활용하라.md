# [Item 23] 태그 달린 클래스보다 클래스 계층구조를 활용하라

### 태크 달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적이다.

- 클래스 계층구조보다 나쁘다.

```java
public class Figure {

    enum Shape {RECTANGLE,CIRCLE}

    private final Shape shape;
    private int length;
    private int width;
    private int radius;

    public Figure(int radius) {
        this.shape = Shape.CIRCLE;
        this.radius = radius;
    }

    public Figure(int length, int width) {
        this.shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    public int area() {
        switch (shape) {
            caseCIRCLE:
                return (int) Math.PI* (radius * radius);
            caseRECTANGLE:
                return length * width;
            default:
                throw new AssertionError("원이나 사각형이 아니에요");
        }
    }
}
```

태그 달린 클래스에는 단점이 있다.

- 열거 타입 선언, 태그 필드, switch 문 등 쓸데없는 코드가 많다.
- 여러 구현이 한 클래스에 혼합돼 있어 가독성이 나쁘다.
- 다른 의미를 위한 코드도 언제나 함꼐 작성하니 메모리도 많이 사용한다.
- final 필드로 선언하려면 해당 의미에 쓰이지 않는 필드들까지 생성자에 초기화 해야 한다.
- 새로운 의미를 부여할 때 switch 문에 로직을 추가해야한다.

`태그 달린 클래스보다 계층구조를 활용하는 서브타이핑이 훨씬 나은 수단이다.`

계층구조로 바꾸는 방법

- 루트가 될 추상 클래스를 정의
- 태그 값에 따라 동작이 달라지는 메서드들을 루트 클래스의 추상 메서드로 선언
    - 위의 코드에서는 `area()` 메서드가 해당된다.
- 태그 값에 상관없이 동작하는 메서드를 루트 클래스에 일반 클래스로 정의한다.
- 모든 하위 클래스에서 공통으로 사용하는 필드를 루트 클래스로 모아준다.
- 루트 클래스를 확장한 구체 클래스를 의미별로 하나씩 정의한다.
    - Rectangle
    - Circle

```java
public abstract class FigureSolution {
    public abstract int area();
}
```

```java
@Getter
@Builder
@AllArgsConstructor
public class Circle extends FigureSolution {

    private final int radius;

    @Override
    public int area() {
        return (int) Math.PI* (radius * radius);
    }
}
```

```java
@Getter
@Builder
@AllArgsConstructor
public class Rectangle extends FigureSolution {

    private final int length;
    private final int width;

    @Override
    public int area() {
        return length * width;
    }
}
```

간결하고 명확하며, 자신의 책임을 위임해주어 쓸데 없는 코드들이 제거되었다.

이러한 계층구조를 반영할 수 있어 유연성은 물론 컴파일타임 타입 검사 능력을 높여준다는 장점도 있다.

### 정리

- 태그 달린 클래스를 써야 하는 상황은 거의 없다.
- 새로운 클래스를 작성하는 데 태그 필드가 등장한다면 태그를 없애고 계층구조로 대체하는 방법을 생각해보자.
- 기존 클래스가 태그 필드를 사용하고 있다면 계층구조로 리팩터링하는 걸 고민해보자.