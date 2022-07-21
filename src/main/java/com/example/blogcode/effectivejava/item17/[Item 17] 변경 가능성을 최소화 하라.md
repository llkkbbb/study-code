# [Item 17] 변경 가능성을 최소화 하라

불변 클래스란 그 인스턴스의 내부 값을 수정할 수 없는 클래스다. 불변 인스턴스가 가지고 있는 정보는 고정되어 객체가 파괴되는 순간까지 달라지지 않는다.

자바 라이브러리에도 다양한 불변 클래스가 있다.

- String
- 기본 타입의 박싱된 클래스
- BigInteger
- BigDecimal

가변 클래스보다 설계와 구현이 쉬우며 오류가 생길 여지도 적고 안전하다.

### 불변 클래스를 만드는 다섯가지 규칙

- 객체의 상태를 변경하는 메서드를 제공하지 않는다
    - setter() 메서드를 예를 들 수 있겠다.
- 클래스를 확장할 수 없도록 한다.
    - final 키워드를 사용한다.
- 모든 필드를 final로 선언한다.
    - 새로 생성된 인스턴스를 동기화 없이 다른 스레드로 건네도 문제없이 동작하게끔 보장하는 데 필요
- 모든 필드를 private 으로 선언한다.
    - 기본 타입 필드나 불변 객체를 참조하는 필드를 public final로만 선언해도 불변 객체가 되지만, 이렇게 하면 다음 릴리스에서 내부 표현을 바꾸지 못하므로 권장하지 않는다.
- 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.
    
    

### 불변 복소수 클래스

```java
@Getter
public final class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex plus(Complex c) {
        return new Complex(this.re + c.re, this.im + c.im);
    }
}
```

plus() 메서드라는 사칙연산 메서드를 정의했다. 이 사칙연산 메서드는 인스턴스 자신은 수정하지 않고 새로운 인스턴스를 만들어 반환한다.

이처럼 피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍 패턴을 함수형 프로그래밍이라 한다.

이러한 방식으로 프로그래밍하면 코드에서 불변이 되는 영역의 비율이 높아지는 장점을 누릴 수 있다.

### 불변 객체는 스레드 안전하여 따로 동기화할 필요 없다.

다른 스레드에 영향을 줄 수 없으니 불변 객체는 안심하고 공유할 수 있다. 따라서 불변 클래스라면 한번 만든 인스턴스를 재활용하기를 권한다.

가장 쉬운 재활용 방법은 상수로 제공하는 것이다.

```java
public static final ComplexONE= new Complex(1, 1);
public static final ComplexTWO= new Complex(2, 2);
public static final ComplexTHREE= new Complex(3, 3);
```

불변 클래스는 자주 사용되는 인스턴스를 캐싱하여 같은 인스턴스를 중복 생성하지 않게 해주는 정적 팩터리를 제공할 수 있다. 이런 정적 팩터리를 사용하면 여러 클라이언트가 인스턴스를 공유하여 메모리 사용량과 가비지 컬렉션 비용이 줄어든다.

새로운 클래스를 설계할 떄 public 생성자 대신 정적 팩터리를 만들어두면, 클라이언트를 수정하지 않고도 필요에 따라 캐시 기능을 나중에 덧붙일 수 있다.

불변 객체를 자유롭게 공유할 수 있다는 점은 방어적 복사도 필요 없다는 결론으로 이어진다.

- clone()
- 복사 생성자

위의 두가지를 제공하지 않는게 좋다.

### 특징

- 불변 객체는 자유롭게 공유할 수 있고, 불변 객체끼리는 내부 데이터를 공유할 수 있다.
- 객체를 만들 떄 다른 불변 객체들을 구성요소로 사용하면 이점이 많다.
- 불변 객페는 그 자체로 실패 원자성을 제공한다.
- 불변 클래스에도 단점은 있다. 값이 다르면 반드시 독립된 객체로 만들어야야 한다는 것이다.

### 불변 클래스를 설계하는 방법

final로 불변을 제공하는 방법 말고 더 유연한 방법이 있다. 모든 생성자를 private 혹은 private-package로 만들고 public 정적 팩터리를 제공하는 방법이다.

```java
@Getter
public final class Complex {
    private final double re;
    private final double im;

    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }
}
```

package-private 구현 클래스를 원하는 만큼 만들어 활용할 수 있으니 훨씬 유연하고 다음 릴리스에서 객체 캐싱 기능을 추가해 성능을 끌어올리 수도 있다. 패키지 바깥의 클라이언트에서 바라본 이 불변 객체는 사실상 final이다.

public이나 protected 생성자가 없으니 다른 패키지에서는 이클래스를 확장하는 게 불가능하기 때문이다.

### BigInteger와 BigDecimal의 인스턴스를 인수로 받는다면 주의해야 한다.

인수로 받은 타입이 진짜인지 반드시 확인해야 한다. 신뢰할 수 없는 하위 클래스의 인스턴스라고 확인되면, 이 인수들은 가변이라 가정하고 방어적으로 복사해 사용해야 한다.

```java
public BigInteger safeInstance(BigInteger val) {
    return val.getClass() == BigInteger.class ?
            val : new BigInteger(val.toByteArray());
}
```

앞에서 말한 다섯가지 규칙 중 모든 필드를 final로 해야 한다고 했다. 이 규칙은 극단적이여서 “어떤 메서드도 객체의 상태 중 외부에 비치는 값을 변경할 수 없다.” 라고 완화해야 한다.

계산 비용이 큰 값을 나중에 계산하여 final이 아닌 필드에 캐시하여 똑같은 값을 다시 요청하면 캐시해둔 값을 반환하여 계산 비용을 절감하는 것이다.

### 정리

- getter() 가 있다고해서 무조건 setter()를 만들지는 말자. 클래스는 꼭 필요한 경우가 아니라면 불변이여야한다.
- 불변으로 만들 수 없는 클래스라도 변경할 수 있는 부분을 최소한으로 줄이자.
- 다른 합당한 이유가 없다면 모든 필드는 private fianl이어야 한다.
- 생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다.