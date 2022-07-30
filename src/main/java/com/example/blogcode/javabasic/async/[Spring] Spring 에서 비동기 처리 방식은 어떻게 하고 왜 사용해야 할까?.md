# [Spring] Spring 에서 비동기 처리 방식은 어떻게 하고 왜 사용해야 할까?

### 들어가기전.

동기식과 비동기식을 이해하고 있어야 한다. 이 방식을 이해하고 넘어가면 Spring에서 간단히 에노테이션만 붙여주면 적용법은 끝날 것이다. 먼저 동기식을 설명하겠다. `동기식`은 개념은 동시에 처리 하는 방식이라 생각하면 된다. 요청과 응답이 시간이 얼마나 걸리던지 요청한 자리에서 결과가 주어져야 한다. `비동기식`의 개념은 동시에 처리 하는 방식이 아니라 생각하면 된다. 즉, 요청을 보내면 동시에 응답이 일어나지 않는다.

동기식의 설계는 매우 간단하고 직관적이지만 응답이 내려질 때까지 아무것도 못하고 대기해야 하고 교착 상태와 기아 상태가 발생할 수 있다는 단점이 있다.

비동기식의 동기식보다 복잡하지만 응답이 내려지는데 시간이 걸리더라도 다른 스레드가 다른 작업을 할 수 있다. 하지만 스레드 안정하지 못하여 공유 자원을 사용하는 데 안 좋은 상황이 마주칠 것 이다.

부모님이 토스은행에 입금을 하고 자식이 출금을 하는 가정을 동기식 프로그램으로 구현해보자.

```java
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TossBank {

    private int money;

    public int credit(int money) throws InterruptedException {
        int currentMoney = getMoney();
        if (money <= 0) {
            throw new InputMoneyException();
        } else {
            Thread.sleep(2000);
            updateMoney(currentMoney + money);
        }
        return money;
    }

    public int withdraw(int money) throws InterruptedException {
        int currentMoney = getMoney();

        if (currentMoney == 0) {
            throw new NotEnoughMoneyException();
        } else if (money == 0) {
            throw new InputMoneyException();
        } else {
            Thread.sleep(1000);
            updateMoney(currentMoney - money);
        }
        return money;
    }

    public void updateMoney(int money) {
        this.money = money;
    }
}
```

토스 은행에 현재의 잔액이 0원이라면 `NotEnoughMoneyException` 예외가 발생할 것이고 입금 금액과 출금 금액이 0원이면 `InputMoneyException` 예외가 발생하게 구현했다.

현재는 동기식 방식으로 구현했다.

부모와 자식 클래스를 생성한다.

```java
public class Parent extends Thread {
    private TossBank tossBank;
    private int money;

    public Parent(TossBank tossBank, int money) {
        this.tossBank = tossBank;
        this.money = money;
    }

    @Override
    public void run() {
        try {
            tossBank.credit(this.money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("입금합니다." + this.money);
    }
```

```java
public class Child extends Thread {

    private TossBank tossBank;
    private int money;

    public Child(TossBank tossBank, int money) {
        this.tossBank = tossBank;
        this.money = money;
    }

    @Override
    public void run() {
        try {
            tossBank.withdraw(money);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("출금합니다." + this.money);
    }
}
```

```java
public class Main {

    private static TossBanktossBank= new TossBank(0);

    public static void main(String[] args) throws InterruptedException {
        Parent parent = new Parent(tossBank, 10000);
        parent.start();

        Thread.sleep(1000);

        Child child = new Child(tossBank, 4500);
        child.start();
    }
}
```

프로그램을 실행해보면 예외가 발생할 것이다.

![스크린샷 2022-07-31 오전 12.52.19.png](%5BSpring%5D%20Spring%20%E1%84%8B%E1%85%A6%E1%84%89%E1%85%A5%20%E1%84%87%E1%85%B5%E1%84%83%E1%85%A9%E1%86%BC%E1%84%80%E1%85%B5%20%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5%20%E1%84%87%E1%85%A1%E1%86%BC%E1%84%89%E1%85%B5%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AB%20%E1%84%8B%E1%85%A5%E1%84%84%E1%85%A5%E1%87%82%E1%84%80%20597d104290c0416e9ac12979ff3efd3c/_2022-07-31__12.52.19.png)

이처럼 동기식 프로그램은 멀티 스레드 환경에서 공유 자원을 사용하면 스레드 안정하지 못한다는 단점이 있다.

이를 방지하기 위해 비동기식으로 하는데 위의 코드에서 간단하게 구현 해 보기 위해  `synchronized` 키워드를 붙여준다.

![스크린샷 2022-07-31 오전 12.55.18.png](%5BSpring%5D%20Spring%20%E1%84%8B%E1%85%A6%E1%84%89%E1%85%A5%20%E1%84%87%E1%85%B5%E1%84%83%E1%85%A9%E1%86%BC%E1%84%80%E1%85%B5%20%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5%20%E1%84%87%E1%85%A1%E1%86%BC%E1%84%89%E1%85%B5%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AB%20%E1%84%8B%E1%85%A5%E1%84%84%E1%85%A5%E1%87%82%E1%84%80%20597d104290c0416e9ac12979ff3efd3c/_2022-07-31__12.55.18.png)

실행 결과는 성공적으로 출력되었다.

![스크린샷 2022-07-31 오전 12.56.24.png](%5BSpring%5D%20Spring%20%E1%84%8B%E1%85%A6%E1%84%89%E1%85%A5%20%E1%84%87%E1%85%B5%E1%84%83%E1%85%A9%E1%86%BC%E1%84%80%E1%85%B5%20%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5%20%E1%84%87%E1%85%A1%E1%86%BC%E1%84%89%E1%85%B5%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AB%20%E1%84%8B%E1%85%A5%E1%84%84%E1%85%A5%E1%87%82%E1%84%80%20597d104290c0416e9ac12979ff3efd3c/_2022-07-31__12.56.24.png)

지금까지 java 코드에서 동기식 비동기식 처리를 위한 방식을 알아보았고 이제 Spring에서 구현하는 방식을 살펴보자.

동기식은 Lombok 라이브러리에서 지원 해 주는 `@Synchronized` 에노테이션을 사용하여 구현한다.

비동기식은 springframework 에서 지원 해 주는 `@Async` 에노테이션을 사용하여 구현한다.

ComponentScan 의 처음 시작 위치인 클래스에 `@EnableAsync` 선언한다. 이 에노테이션은 비동기식을 활성화 한다는 설정이다.

![스크린샷 2022-07-31 오전 1.04.42.png](%5BSpring%5D%20Spring%20%E1%84%8B%E1%85%A6%E1%84%89%E1%85%A5%20%E1%84%87%E1%85%B5%E1%84%83%E1%85%A9%E1%86%BC%E1%84%80%E1%85%B5%20%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5%20%E1%84%87%E1%85%A1%E1%86%BC%E1%84%89%E1%85%B5%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AB%20%E1%84%8B%E1%85%A5%E1%84%84%E1%85%A5%E1%87%82%E1%84%80%20597d104290c0416e9ac12979ff3efd3c/_2022-07-31__1.04.42.png)

이렇게 하고 비동기식으로 구현하고 싶은 기능이 있으면 그 기능에 `@Async` 에노테이션을 선언하면 된다.

다음으로는 `@Configuration` 에 `@Bean` 으로 등록하고 쓰레드 풀 설정을 사용자 지정으로 설정 할 수 있고 `@Async` 에노테이션에 빈 이름을 옵션으로 넣어주면 비동기식 구현은 끝난다.

![스크린샷 2022-07-31 오전 1.14.49.png](%5BSpring%5D%20Spring%20%E1%84%8B%E1%85%A6%E1%84%89%E1%85%A5%20%E1%84%87%E1%85%B5%E1%84%83%E1%85%A9%E1%86%BC%E1%84%80%E1%85%B5%20%E1%84%8E%E1%85%A5%E1%84%85%E1%85%B5%20%E1%84%87%E1%85%A1%E1%86%BC%E1%84%89%E1%85%B5%E1%86%A8%E1%84%8B%E1%85%B3%E1%86%AB%20%E1%84%8B%E1%85%A5%E1%84%84%E1%85%A5%E1%87%82%E1%84%80%20597d104290c0416e9ac12979ff3efd3c/_2022-07-31__1.14.49.png)