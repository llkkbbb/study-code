# Generic Programing

클래스에서 사용하는 변수의 자료형이 여러개 일 수 있고, 그 기능이 동일한 경우 클래스의 자료형을 정하지 않고 추후 해당 클래스를 사용할 때 지정 할 수 있도록 선언해주는 프로그래밍이다.

실제 사용되는 자료형의 반환은 컴파일러에 의해 검증되므로 기존에 instancof 연산자로 타입 체크의 수고를 덜어주고 의도치 않은 타입의 객체가 저장되는 것을 막고, 저장된 객체를 꺼내올 때 원래의 타입과 다른 타입으로 잘못 형변환되어 발생할 수 있는 오류들을 줄여든다.

`한마디로 객체의 타입 안정성과 형변환의 번거로움을 줄인다. 라는 장점`이 있다. 주로 사용하는 곳은 컬렉션 프레임워크와 jpa 등 여러 곳에서 많이 쓰이는 것을 볼 수 있다.

### Generic의 장점을 보여주는 예제 구현

```java
public class Bread {

    private RiceFlour material;
 
}
```

빵은 쌀가루라는 재료를 갖고 있다. 하지만 쌀가루라는 재료만 갖고 있지 않을 것이다. 밀가루라든가 등 여러가지 재료들이 많을 것이다.

그래서 쌀가루타입을 참조하지 않고 Object 타입으로 참조하여 형변환이 가능하도록 다시 만들어보자.

```java
public class Bread {

    private Object material;

}
```

Object는 모든 클래스의 상위 클래스이므로 형변환이 이루어져야 한다.

그러면 Generic 프로그래밍으로 코드를 바꿔보자.

```java
public class Bread<T> {

    private T material;

}
```

위의 코드처럼 구현하면 재료가 어떤 재료가 와도 타입체킹을 하지 않고 형변환도 하지 않는 코드가 될 것이다.

### Generic 은 <T extends 클래스> 를 사용하면 T 자료형의 범위를 제한 할 수 있게 해준다.

재료라는 추상클래스를 상속한 쌀가루와 밀가루를 예시로 들어보자.

```java
public class Bread<T extends Material> {

    private T material;

}
```

Material 클래스와 하위 클래스들의 범위로 제한하여 자료형을 참조할 수 있다.

### 와일드 카드 (**wild card)**

타입을 어떻게 사용해야 와일드 카드인지 사전적 의미는 무엇인지 일단은 알아보자.

- 사전적 의미

```java
여러 파일을 한꺼번에 지정할 목적
```

참조 : [https://ko.wikipedia.org/wiki/와일드카드_문자](https://ko.wikipedia.org/wiki/%EC%99%80%EC%9D%BC%EB%93%9C%EC%B9%B4%EB%93%9C_%EB%AC%B8%EC%9E%90)

- 사용법

```java
<? extends T> T와 그 하위 클래스만 가능

<? super T> T와 그 상위 클래스만 가능

<?> 모든 타입이 가능 -> <? extends Object> 와 동일
```

예제 코드로 와일드 카드의 장점을 분석해보자.

재료를 가지고 빵을 만드는 제빵사가 있는데 제빵사는 그냥 재료라는 타입만 가지고 빵을 만든다.

```java
public class Baker {

    public static Bread makeBread(List<Material> materials) {
        // 빵 만드는 로직
        return new Bread(materials.get(0));
    }
}
```

매개변수 타입을 List<Material> 로 고정 시켜 놨다. 재료의 쌀가루로 빵을 만들어 보자.

```java
@Test
void BreadTest() {
    List<Material> materials = new ArrayList<>();
    materials.add(new Material());

    List<RiceFlour> riceFlours = new ArrayList<>();

    Baker.makeBread(materials);
    Baker.makeBread(riceFlours); // 컴파일 에러
}
```

매개변수 타입을 <Material> 로 고정 시켜 놓아서 riceFlours는 컴파일 에러가 발생한다.

와일드 카드로 정의 해 놓으면 컴파일 에러가 발생이 안된다.

```java
public class Baker {

    public static Bread makeBread(List<? extends Material> materials) {
        // 빵 만드는 로직
        return new Bread(materials.get(0));
    }
}
```

다음으로 와일드 카드는 다중 경계의 표현이 안된다. 잘 알아 두자.

와일드 카드를 주로 사용하는 라이브러리나 프레임워크들을 많이 다루므로 잘 이해해 두자.

### 제네릭 메서드 (Generic Method)

매개변수에 복잡한 와일드 카드가 정의 되어 있는 변수 들이 많으면 가독성이 많이 떨어지게 될 것이다.

이를 위한 것이 제네릭 메서드다.

위의 빵만드는 기능에 와일드 카드가 정의 된 매개변수를 봐 보자. 이를 제네릭 메서드로 만들어보자.

```java
public static <T extends Material> Bread makeBread(List<T> materials) {
    // 빵 만드는 로직
    return new Bread(materials.get(0));
}
```

반환 타입의 옆에 와일드 카드를 정의해주고 매개변수에는 타입만 명시해주면 된다.

만약 매개 변수가 여러개일 때는 해당 매개 변수 타입이 같은 타입이 와야된다는 점을 잊지말자.

### 제네릭 타입의 제거

컴파일러는 제네릭 타입을 이용해서 소스파일을 체크하고, 필요한 곳에 형변환을 넣어 주고 제네릭 타입을 제거한다.

즉, 컴파일된 파일(*.class)에는 제네릭 타입에 대한 정보는 없다.

기본적인 제거 과정

1. 제네릭 타입의 경계(bound)를 제거한다.

제네릭 타입이 <T extends Material> 라면 T는 Material로 치환된다. <T> 인 경우는 T는 Object 로 치환된다. 그리고 클래스 옆의 선언은 제거된다.

```java
public class Baker <T extends Material> {

    public void addMaterial(T t) {
        // 재료 입고....
    }
}
```

위의 코드가 아래 코드로 변한다.

```java
public class Baker {

    public void addMaterial(Material t) {
        // 재료 입고....
    }
}
```

1. 제네릭 타입을 제거한 후에 타입이 일치하지 않으면, 형변환을 추가한다.

```java
public T getMaterial(int i) {
    return materials.get(i);
}
```

materials 의 제네릭을 제거 하고 제네릭 메서드의 제네릭도 제거한다. 그러면 materials.get() 메서드는 object를 반환해서 형변환이 필요하다. 형변환을 해준다.

```java
public Material getMaterial(int i) {
    return (Material) materials.get(i);
}
```