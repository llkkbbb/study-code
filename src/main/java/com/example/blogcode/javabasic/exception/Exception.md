# Exception

### 예외 처리의 중요성

- 비정상 종료를 방지
- 로그를 잘 기록하여 버그의 상황이 어떤 것인지 확인

아래 예제 코드를 보면 예외 처리의 중요성을 잘 알게 될 것이다.

```java
int[] arr = new int[10];

try {
    for (int i = 0; i < arr.length + 1; i++) {
        arr[i] = i;
    }
} catch (ArrayIndexOutOfBoundsException e) {
    e.printStackTrace();
    String message = e.getMessage();
    System.out.println(message);
}

// 예외를 처리하고 정상 수행이 된다.
// 비정상 종료가 되지 않는다.
System.out.println(Arrays.toString(arr));
```

배열의 길이에 1을 더한 만큼 반복하여 배열에 값을 넣어준다. 하지만 여기서 런타임 에러가 발생할 것이다. 

만약 예외처리를 안했다면 비정상으로 종료가 되었을 것이고 로그를 남기지 않아 찾기가 힘들것이다. 스택 트레이스 더미에서 찾아 내야한다. 하지만 로그를 남기면 찾기가 수월 할 것이다.

### 프로그램 오류

소스코드를 컴파일하면 컴파일러가 소스코드에 대해 검사를 수행하여 오류가 있는지 알려준다. 컴파일러가 알려주는 에러는 컴파일 에러로 분류된다.

- 컴파일 에러 → 컴파일시에 발생하는 예외

컴파일을 성공적으로 마치면 클래스 파일이 생성되고 생성된 클래스 파일을 실행할 수 있게 된다. 실행 시에 발생 되는 에러는 런타임 에러로 분류된다.

- 런타임 에러 → 실행 시에 발생하는 에러

### 예외 클래스의 계층구조

자바에서는 실행 시 발생할 수 있는 오류를 클래스로 정의한다. Exception과 Error 클래스들은 모두 Object 자손들이다.

![Untitled](../../../../../../../../../../Desktop/노션%20파일/Export-204afd82-14c6-4c3a-857b-1afaf7a02c66/Exception%20f09b8ecff8d249eab784324554763ebc/Untitled.png)

모든 예외의 최고 조상은 Exception 클래스이다.

![Untitled](../../../../../../../../../../Desktop/노션%20파일/Export-204afd82-14c6-4c3a-857b-1afaf7a02c66/Exception%20f09b8ecff8d249eab784324554763ebc/Untitled%201.png)

위 그림에서 볼 수 있듯이 두 그룹으로 나눠질 수 있다.

- `RuntimeException` 클래스와 자손들
    - 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
    - `unChecked Exception` 으로 구분한다.
- `IOException, ClassNotFoundException`
    - 개발자의 실수로 발생하는 예외
    - `Checked Exception` 으로 구분한다.

이 외에도 더 많은 클래스들이 있다.

### 예외처리하기 try-catch문

예외처리의 목적은 프로그램의 비정상 종료를 막고, 정상적인 실행상태를 유지하는 것이다.

try-catch의 구조는 아래와 같다.

```java
try {
     // 예외가 발생할 가능성이 있는 문장
} catch(Exception1 e1) {
    // Exception1이 발생했을 경우 이를 처리하기 위한 문장
} catch(Exception2 e2) {
    // Exception2이 발생했을 경우 이를 처리하기 위한 문장
} catch(Exception3 e3) {
    // Exception3이 발생했을 경우 이를 처리하기 위한 문장
} ..........

```

여러 종류의 예외를 처리할 수 있으나 try 블럭에서 발생된 예외의 종류가 일치한 catch 블럭만 처리가 된다.

발생한 예외가 없으면 아무런 처리를 하지 않는다.

catch 블럭에서의 예외 인스턴스에는 방생한 예외의 정보가 담겨져있다.

- printStackTrace() → call stack에 있었던 메서드의 정보와 예외 메시지를 화면에 출력
- getMessage() → 발생한 예외 인스턴스에 담겨져 있는 메시지를 출력

### 예외 던지기

예외를 `고의로 발생`시킬 수 있다. 방법은 아래와 같다.

```java
throw new Exception();
```

### 메서드에 예외 선언하기

메서드에 예외를 throws 라는 키워드로 선언할 수 있다. 하지만 예외를 처리하는 것이 아니라 자신을 호출한 메서드에게 예외를 전달하여 예외처리를 떠맡기는 것이다.

메서드에 예외를 선언하면 장점이 `발생 가능성이 있는 예외들을 명시적으로 선언`할 수 있고 `코드를 안정적으로 작성`할 수 있다. 

```java
public void test() throws Exception1, Exception2........ {
}
```

### finally 블럭

예외의 발생여부에 상관없이 실행되어야할 코드를 포함시킨다. 구조는 아래와 같다.

```java
try {
     // 예외가 발생할 가능성이 있는 문장
} catch(Exception1 e1) {
    // 예외처리를 위한 문장
} finally {
   // 예외 발생여부에 관계없이 항상 수행
}

```

### try-with-resource

자바 7 부터 추가되었다. 이 구문은 사용했던 자원을 자동반환 할 수 있게 해주는 구문이다. `AutoCloseable` 인터페이스를 구현한 것이 있어야 한다. 구조는 아래와 같다.

```java
try(// AutoCloseable 인터페이스를 구현한 객체 인스턴스) {
} catch(Exception e) {
}
```

### 사용자정의 예외 만들기

Exception을 상속받아 checked 예외로 작성하는 경우에서 요즘은 예외처리를 선택적으로 할 수 있도록 RuntimeException을 상속받아서 작성하는 쪽으로 지향한다.

```java
public class MyException extends RuntimeException {

    private final int ERROR_CODE;

    public MyException(int errorCode) {
        this.ERROR_CODE = errorCode;
    }

    public MyException(String message, int errorCode) {
        super(message);
        this.ERROR_CODE = errorCode;
    }

}
```

.