# Inheritance in Java

### 자바에서 상속이란?

OOP에서 상속은 기존 코드를 재사용하거나 기존 유형을 확장하는 용도로 사용합니다.

자바에서 클래스는 다른 클래스와 여러 인터페이스를 상속할 수 있는 반면 인터페이스는 다른 인터페이스를 상속할 수 있습니다.

### 필요성

게임들의 캐릭터를 객체화를 시켜봅시다. 캐릭터라는 클래스는 여러가지 직업들을 가질 수 있고 무기도 가질 수 있어 너무 추상적인 클래스입니다. 기본적인 특징만 정의하고 이를 재사용하는 상속이 필요합니다. 

```java
public class Character {

		private String weapon;
    private int level;
    private long hp;
    private long mp;

    // 스텟 클래스를 따로 만들어 참조하는 식으로 하는게 좋을거 같다!
    private int str; // 스텟
    private int dex; // 스텟
    private int intelligence; // 스텟
    private int lux; // 스텟

		public void attack(String weapon) {
        // weapon 에 따라 공격이 달라진다!
    };

}
```

### 클래스 상속

`extends` 키워드를 사용하여 상속을 합니다.  클래스의 상속은 `단일 상속`만 되고 클래스의 상속 관계는 is a 관계로 캐릭터는 도적이다 라는 관계가 성립됩니다. 상속을 하면 상속한 클래스의 속성들을 접근 할 수 있어 재사용을 한다는 장점이 있습니다..

위의 Character 클래스의 속성들을 재사용하여 Rogue클래스를 만들어봅시다.

```java
public class Rogue extends Character {

    private String skill; // 스킬

}
```

Character 의 속성과 기능을 상속하여 구현 하였습니다. 상속시에 생성자가 필히 있어야 하는데 예제 코드에는 생성자가 보이질 않습니다. 이 예제들을 복사하여 컴파일 환경에 코드를 작성해보면 상속에 대한 에러는 없을 것입니다. 이는 `컴파일러`가 `기본 생성자`를 생성해주는 것을 알 수 있습니다. 하지만 기본 생성자 외 매개변수를 받는 생성자만 생성되어 있다면 컴파일러는 이를 해결해주지 않아 기본 생성자를 생성해주어야 합니다.

### 인터페이스 상속(구현)

Character의 기능 중 공격이라는 기능이 모든 캐릭터의 공통된 기능입니다. 인터페이스를 상속하는 관계에는 has a 관계로 캐릭터는 공격기능을 가진다 라는 관계가 성립되어 인터페이스로 기능을 빼줄 수 있다.

```java
public interface BasicAttack {
    void attack(String weapon);
}
```

위의 코드는 메서드 시그니처만 정의되어 있는 인터페이스입니다. 이를 구현한 클래스들은 매번 공격이라는 메서드를 구현 해야 번거로움이 있습니다. 이는 자바 8 이전에 인터페이스의 한계를 보여주고 있습니다.

하지만 자바 8부터는 기본 메서드를 구현 할 수 있는 스펙이 생겼습니다.

```java
public interface BasicAttack {
    default void attack(String weapon) {
        // 무기에 따라 달라지는 로직!
    };
}
```

다음으로 인터페이스는 다중 상속 (구현) 이 가능합니다. 

```java
public class Character implements BasicAttack, Skill {
		,,,,,,
}
```

또한 인터페이스를 확장해 나가는 방식으로 상속이 가능합니다.

```java
public interface BasicAttack extends Skill {
		,,,,,
}
```

예제코드가 부적절 한데,,,, 다중 상속이 가능하고 인터페이스들과 상속이 가능하다는 점을 잘 알 고 있어야합니다.