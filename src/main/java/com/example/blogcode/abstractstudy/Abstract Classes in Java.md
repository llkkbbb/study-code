# Abstract Classes in Java

추상 클래스란 공통된 기능과 속성들을 정의한 클래스를 말한다.

공통된 기능은 메서드를 구현하여 한 곳에서 사용할 수 있게 정의해두고 공통된 기능인데 내부 로직을 다르게 만들어야 한다면 메서드 시그니처만 정의 해주면 된다.

추상 클래스는 is a 관계인 객체에 사용한다. 

is a 관계인 “테슬라와 현대차는 자동차다" 로 예를 들어보자.

테슬라는 자율주행 모드를 지원하는 대표적인 차량이다. 현대차도 최근에 자율주행 모드를 지원하는 차량을 많이 판매하고 있다.

두 차량의 공통된 기능은 `자율주행모드` 다. 하지만 자율주행모드를 구현하는 로직은 서로 다를 것이다. 같으면 내부 스파이가 있을 수 도!!!!

차량들을 클래스로 만들어 구현해보자.

- 자동차 → 상위 클래스

```java
public abstract class Car {

    // fields....

    public abstract void modeChange();
}
```

- 테슬라

```java
public class Tesla extends Car {

    // fields....

    @Override
    public void modeChange() {
        System.out.println("테슬라 자율주행 로직");
    }
}
```

- 현대

```java
public class Hyundai extends Car {

    // fields....

    @Override
    public void modeChange() {
        System.out.println("현대 자율주행 로직");
    }
}
```

이렇게 공통된 기능을 한 곳에 모아 추상 클래스로 정의 하여 상속하는 방식으로 구현 해보았다.

하지만 여기서 Car라는 추상클래스를 상속받는 모든 클래스는 자율주행모드인 modeChange() 기능을 오버라이딩 해야하는 문제점이 있다. 

이 문제점을 다음에는 인터페이스로 풀어보겠습니다. 오늘은 이만!!!!