# [Item 16] public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

객체 지향 프로그램은 public 클래스에 public 필드를 사용하는 것은 위험한 일이다. private 으로 변경하고 이를 제공 할 접근자를 추가한다. 접근자는 `getter()` 이다. 아래 코드는 lombok으로 대체 했다.

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Point {
    // public 필드 말고 private 필드를 사용하고 접근자를 추가하여 사용하자
    private int x;
    private int y;
}
```

접근자를 제공함으로써 클래스 내부 표현 방식을 언제들 바꿀수 있는 `유연성`을 제공한다.

package-private(default) 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출할 때가 좋을 떄도 있다.

### 필드가 불변이라면?

필드가 불변이라면 직접 노출할 때의 단점이 조금은 줄어들지만, 결코 좋은 생각이 아니다.

api를 변경하지 않고는 표현 방식을 바꿀 수 없고, 필드를 읽을 때 부수 작업을 수행할 수 없다는 단점이 존재한다.

단, 불변식은 보장한다.

아래코드는 각 인스턴스가 유효한 시간을 표현함을 보장하는 코드이다.

```java
public final class Time {
    private static final intHOURS_PER_DAY= 24;
    private static final intMINUTES_PER_HOUR= 60;

    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour >=HOURS_PER_DAY)
            throw new IllegalArgumentException("시간: " + hour);
        if (minute < 0 || minute >=MINUTES_PER_HOUR)
            throw new IllegalArgumentException("분: " + minute)
        this.hour = hour;
        this.minute = minute;
    }
}
```

### 정리

- public 클래스는 절대 가변 필드를 직접 노출해서는 안된다.
- 불변 필드라면 노출해도 덜 위험하지만 안심할 순 없다.
- private-package 클래스나 private 중첩 클래스에서는 종종 필드를 노출하는 편이 나을 때도 있다.