# Inner Class

클래스 내부에 선언한 클래스로 중첩 클래스라고도 말한다. 이 클래스를 둘러싼 외부 클래스와 밀접한 연관이 있는 경우가 많다.

내부 클래스의 종류

- 인스턴스 내부 클래스
- 정적(Static) 내부 클래스
- 지역(local) 내부 클래스
- 익명(anonymous) 내부 클래스

### 인스턴스 내부 클래스

- 내부적으로 사용할 클래스를 private으로 선언하기를 권장한다.
    - private 이 아닌 다른 접근 제어자를 사용하면 다른 외부 클래스에서 생성할 수 있는 문제.
- 외부 클래스가 생성된 후에 생성된다.

예시

```java
// Outer 를 생성한 후
Outer outer = new Outer();
// Inner 생성 가능
Outer.Inner inner = outer.new Inner();
```

생성자를 사용하여 내부 클래스를 생성하는 것도 가능하다.

```java
public class Instance {

    public Instance() {
        this.inClass = new InClass();
    }

    private class InClass {
		}
}
```

내부 클래스 관점에서 내부 클래스의 변수들은 지역 특성을 가지고 외부 클래스의 변수들은 전역 특성을 가진다.

```java
public class Instance {

    private int num = 10;
    private static intsNum= 20;
    private InClass inClass;

    private class InClass {
        int inNum = 30;
				// static int inSNum = 40; // 컴파일 에러!
        public void test() {
            System.out.println("외부 클래스의 num = " + num);
            System.out.println("외부 클래스의 sNum = " +sNum);
            System.out.println("내부 클래스의 inNum = " + inNum);
        }
    }
}
```

내부 클래스에 static 변수를 사용하면 컴파일 에러가 난다. static 을 사용하려면 내부 클래스를 static 클래스로 변경 해야한다.

### 정적 내부 클래스

외부 클래스 생성과 무관하게 사용할 수 있고 정적 변수, 정적 메서드를 사용 할 수 있다.

```java
public class Instance {

    private int num = 10;
    private static intsNum= 20;
    private InClass inClass;

    public static class InStaticClass {
        int inNum = 50;
        static intinSNum= 60;

        public void test() {
//            System.out.println("외부 클래스의 num = " + num); // 인스턴스 변수는 사용하지 못함
            System.out.println("내부 클래스의 inNum = " + inNum);
            System.out.println("내부 클래스의 inSNum = " +inSNum);
            System.out.println("외부 클래스의 SNum" +sNum); // 외부 클래스의 정적 변수는 사용가능
        }

    }
}
```

### 지역 내부 클래스

- 지역 변수와 같이 메서드 내부에서 정의하여 사용하는 클래스다.
- 메서드 호출이 끝나면 메서드에 사용된 지역변수의 유효성은 사라진다.
- 메서드 호출 이후에도 사용해야 하는 경우가 있을 수 있으므로 내부 클래스에서 사용하는 메서드의 지역 변수나 매개 변수는 final로 선언된다.

```java
public class Outer {

    Runnable getRunnable(int i) {
        int num = 100;

        class MyRunnable implements Runnable {

            int localNum = 10;
            @Override
            public void run() {
//                num = 200; // 컴파일 에러! 상수로 바뀜
//                i = 20; // 컴파일 에러! 상수로 바뀜

            }
        }
        return new MyRunnable();
    }
}

```

외부 클래스의 인스턴스 변수나 클래스 정적 변수들을 사용할 수 있다.

### 익명 내부 클래스

- 스레드를 만들때 자주 사용하는 Runnable 을 사용한다.
- 클래스의 이름을 생략하고 주로 하나의 인터페이스나 하나의 추상 클래스를 구현하여 반환한다.

```java
public class Outer {

    Runnable runnable() {
        return new Runnable() {
            @Override
            public void run() {
								/// 로직
            }
        };
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
						// 로직
        }
    };
}
```