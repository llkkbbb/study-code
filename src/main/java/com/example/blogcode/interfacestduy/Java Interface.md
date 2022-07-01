# Java Interface

인터페이스는 추상 클래스와 동일한 미완성 설계도?? 추상적인 계층이다.

객체 지향 관점에서 추상 클래스는 is a 관계에 풀어서 사용하고 인터페이스는 `has a 관계`에 풀어서 사용한다. 이점을 꼭 기억하자.

추상 클래스에 관한 글에 Car클래스의 자율주행기능 modeChange() 메서드를 따로 인터페이스로 빼서 구현해보겠습니다,

```java
public interface DrivingFunction {

    void modeChange();
}
```

DrivingFunction 인터페이스는 자율주행 기능을 정의해놓았다. 그냥 기본 틀이고 구현은 안되어있다.

자율주행 기능이 있는 차들에게만 이 기능을 구현 해주면 된다.

```java
public class Tesla implements DrivingFunction {

    // fields....

    @Override
    public void modeChange() {
        System.out.println("테슬라 자율주행 로직");
    }
}
```

하지만 테슬라 차량에 자율주행 로직이 동일하다면 매번 테슬라 차량에게 동일한 로직들을 구현 해줘야 합니다.

자바 8 이전에 인터페이스는 메서드 시그니처와 상수들만 정의 할 수 있었습니다.

하지만 자바 8 이후에는 디폴드 메서드와 스태틱 메서드가 지원되었습니다.

defaul 메서드로 자율 주행 기능을 빼보겠습니다.

```java
public interface DrivingFunction {
    
    // 테슬라 자율 주행 기능
    default void changMode() {
        System.out.println("자율 주행 기능 로직");
    }

}
```

위의 인터페이스를 Tesla에 구현 하여 매번 changMode() 를 구현해야하는 문제점을 해결했다.

```java
public class Tesla implements DrivingFunction {

    // fields....
}
```

하지만 테슬라 차량에 자율주행 기능 로직이 인터페이스의 디폴트 메서드와 다르다면? 어떻게 해결 해야할까요?

default 메서드는 기본 메서드이므로 오버라이딩하여 메서드 재정의를 해주면 된다.

```java
public class Tesla implements DrivingFunction {

    // fields....

    @Override
    public void changMode() {
        System.out.println("로직 변경....");
    }
}
```

이렇듯 인터페이스는 has a 관계의 공통 된 기능을 한 곳에 모아 구현 할 수 있도록 해주는 아주 중요한 기능이다.