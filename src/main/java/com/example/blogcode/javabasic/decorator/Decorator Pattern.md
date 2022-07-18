# Decorator Pattern

데코레이터 패턴은 디자인 패턴 중 하나이다.

데코레이터 패턴을 사용하여 정적으로 또는 동적으로 객체에 추가 책임을 추가할 수 있다. 데코레이터는 원래 객체에 대한 향상된 인터페이스를 제공한다.

이 패턴을 구현할 시 상속보단 구성을 위주로 각 데코리에터 요소에 대해 반복해서 상속하는 오버헤드를 줄일 수 있다.

코테를 준비하시는 분이시라면 아래와 같은 코드를 작성 해본적이 있을 것이다.

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
```

기반 스트림으로 읽고 보조 스트림으로 추가적인 기능을 제공하는 구조로 테코레이터 패턴의 대표적인 예시가 된다.

이는 객체의 결합을 통해 기능을 동적으로 유연하게 확장할 수 있는 장점이 있다.

크리스마스 트리 개체가 있고 여러가지 장식물로 꾸미고 싶다고 가정해보자.

아래의 그림처럼 구성을 가질것이다.

![https://blog.kakaocdn.net/dn/dRxBHs/btrHppdYGT6/iKEnLaUJ5vwQLfVR4ZKVLK/img.jpg](https://blog.kakaocdn.net/dn/dRxBHs/btrHppdYGT6/iKEnLaUJ5vwQLfVR4ZKVLK/img.jpg)

크리스마스 트리라는 컴포넌트를 인터페이스로 정의합니다.

```java
public interface ChristmasTree {

	public String decorator();
}
```

구체적인 컴포넌트를 구현합니다.

```java
public class ChristmasTreeImpl implements ChristmasTree {

	@Override
	public String decorator() {
		return "Christmas Tree";
	}
}
```

이제 이 트리의 대한 추상 데코레이터 클래스를 만든다. 이 데코레이터는 앞에서 정의한 인터페이스 ChrismasTree를 구현하고 동일한 객체를 보유 하고, 동일한 인터페이스에서 구현된 메서드를 데코레이터 클래스에서 호출한다.

```java
public abstract class TreeDecorator implements ChristmasTree {

	private ChristmasTree christmasTree;

	public TreeDecorator(ChristmasTree christmasTree) {
		this.christmasTree = christmasTree;
	}

	@Override
	public String decorator() {
		return christmasTree.decorator();
	}
}
```

이제 장식용 클래스는 위의 TreeDecorator 클래스를 확장하여 추가적인 기능만 제공하면 된다.

```java
public class BubbleLights extends TreeDecorator {

	public BubbleLights(ChristmasTree christmasTree) {
		super(christmasTree);
	}

	@Override
	public String decorator() {
		return super.decorator() + decoratorWithBubbleLights();
	}

	private String decoratorWithBubbleLights() {
		return " with Bubble Lights";
	}
}
```

```java
public class BubbleLights extends TreeDecorator {

	public BubbleLights(ChristmasTree christmasTree) {
		super(christmasTree);
	}

	@Override
	public String decorator() {
		return super.decorator() + decoratorWithBubbleLights();
	}

	private String decoratorWithBubbleLights() {
		return " with Bubble Lights";
	}
}
```

```java
ChristmasTree tree = new Garland(new ChristmasTreeImpl());
System.out.println(tree.decorator());

ChristmasTree tree1 = new BubbleLights(new Garland(new ChristmasTreeImpl()));
System.out.println(tree1.decorator());
```

위에서 말했듯이 이 패턴은 런타임에 원하는 만큼 데코레이터를 추가할 수 있는 유연성을 제공한다.

객체의 동작이나 상태를 추가, 향상 또는 제거하려는 경우와 클래스의 단일 객체의 기능을 수정하고 나머지는 변경하지 않으려는 경우에 사용하면 좋습니다.

참조 :

[https://www.baeldung.com/java-decorator-pattern](https://www.baeldung.com/java-decorator-pattern)