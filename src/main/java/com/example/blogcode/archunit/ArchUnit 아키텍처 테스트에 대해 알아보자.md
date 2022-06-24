# ArchUnit 아키텍처 테스트에 대해 알아보자

## 아키텍처 테스트 소개

개발 공부를 하면서 단위 테스트 통합 테스트 등에 대한 내용들은 익숙해졌다.

위에서 말한 2가지 테스트 외에도 속성 테스트와 아키텍처 테스트 등 여러 테스트가 존재 하는데 오늘은 아키텍처 테스트에 대한 내용과 패키지 종속성 검사와 클래스 종속성 검사에 대한 테스트를 알아볼 것입니다.

나머지 deep한 내용들은 하단에 reference 링크가 있으니 관련 테스트를 할 시에 참조하여 살펴봅시다.

아키텍처란 건축학이라는 뜻을 가지며 소프트웨어에선 시스템의 구성을 뜻합니다.

이러한 뜻을 보며 아키텍처 테스트를 생각해보면 `“패키지 구조와 클래스들 간의 관계 등 을 테스트 해볼 수 있을 것이다”` 라고 예측할 수 있습니다. 

아키텍처 테스트의 목적은 `아키텍처의 패키지, 클래스, 레이어, 슬라이스간의 의존성을 확인하고 객체간의 참조를 확인하여 적절하게 리팩토링` 하는 것입니다.

### 아키텍처 테스트의 예시를 살펴보자.

- A 라는 패키지가 B (또는 C, D) 패키지에서만 사용 되고 있는지 확인할 수 있다.
- Service 라는 이름의 클래스들이 Controller 또는 Service 라는 이름의 클래스에서만 참조하고 있는지 확인할 수 있다.
- Service 계층의 클래스들이 service 패키지에 포함되어 있는지 확인할 수 있다.
- A라는 애노테이션을 선언한 메소드만 특정 패키지 또는 특정 애노테이션을 가진 클래스를 호출하고 있는지 확인할 수 있다.
- 특정한 스타일의 아키텍처를 따르고 있는지 확인할 수 있다.

## ArchUnit 라이브러리 소개

ArchUnit 라이브러리는 아키텍처 테스트를 위한 라이브러리입니다. 

단위 테스트와 동일한 방법으로 작성되며 아키텍처의 패키지, 클래스, 레이어, 슬라이스간의 의존성을 확인하고 객체간의 참조를 확인할 수 있게 도와줍니다.

동작 원리는 특정 패키지에 해당하는 클래스의 `바이트 코드`를 읽어와 확인해야 할 제약조건(규칙)을 정의하고 확인합니다.

- build.gradle

```java
testImplementation 'com.tngtech.archunit:archunit-junit5:0.23.1'
```

참조 : [https://mvnrepository.com/artifact/com.tngtech.archunit/archunit-junit5-api](https://mvnrepository.com/artifact/com.tngtech.archunit/archunit-junit5-api)

테스트를 진행하기 전 간단한 패키지 구조와 프로젝트 구조를 그림으로 살펴보겠습니다.


![스크린샷 2022-06-18 오전 12 57 35](https://user-images.githubusercontent.com/82162844/175452519-8b53116e-595c-415f-a13d-f6916c104169.png)


여기서 domain 패키지에 repository와 domain을 같이 묶어 두었습니다.

나머지는 기본적인 web 계층 layer 구조입니다.

## 시작

### 1. 클래스 가져오기

ArchUnit 은 자바 바이트 코드를 자바 코드 구조로 가져오기 위한 기능을 제공한다.

```java
JavaClasses classes = 
							new ClassFileImporter().importPackages("com.example.blogcode");

```

### 2. 제약조건 정의하기

“service 패키지내에 있는 클래스들은 controller 패키지에만 참조 해야 한다” 라는 제약조건을 정의할 수 있습니다.

```java
ArchRule myRule =classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");
```

ArchRule 타입으로 정의한 제약조건과 읽어온 패키지의 정보를 매치하여 제약조건이 일치하는지 확인합니다.

```java
 myRule.check(classes);
```

위의 내용으로 합친 완전한 테스트 코드

```java
@Test
@DisplayName("간단한 테스트")
void simple_test() {
    JavaClasses classes = new ClassFileImporter().importPackages("com.example.blogcode");

    ArchRule myRule =classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

    myRule.check(classes);
}
```

### 3. Junit 5 사용

```java
@AnalyzeClasses(packages = "com.example.blogcode")
public class ArchTests {

    @ArchTest
    public ArchRule myRule =classes()
            .that().resideInAPackage("..service..")
            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");
}
```

`@AnalyzeClasses` 애노테이션은 클래스를 읽어들여 확인할 패키지를 설정할 수 있게 해줍니다.

정의한 제약조건(규칙)에 `@ArchTest` 애노테이션이 달린 규칙을 확인합니다.

### 4. 패키지 종속성 확인

domain 패키지내에서 controller 패키지를 참조하면 안된다. 이를 확인하는 제약조건을 정의한다.

```java
@ArchTest
ArchRule myRule = noClasses().that().resideInAPackage("..domain..")
        .should().dependOnClassesThat().resideInAPackage("..web..");
```

### 5. 클래스 종속성 확인

Controller 클래스는 Service 클래스에 종속되어 있다.

```java
@ArchTest
ArchRule classDependencyCheck =classes()
        .that().haveNameMatching(".*Controller")
        .should().onlyHaveDependentClassesThat().haveNameMatching(".*Service");
```

### 6. 클래스 및 패키지 분리 확인

분리되어 있는 레이어들을 확인하다.

```java
@ArchTest
LayeredArchitecture classAndPackageLayerIsolationCheck =layeredArchitecture()
        .layer("web").definedBy("..web..")
        .layer("service").definedBy("..service..")
        .layer("domain").definedBy("..domain..")
        .whereLayer("web").mayNotBeAccessedByAnyLayer()
        .whereLayer("service").mayOnlyBeAccessedByLayers("web")
        .whereLayer("domain").mayOnlyBeAccessedByLayers("service");
```

아키텍처 테스트에 대한 deep한 내용은 reference에서 자세하게 나와 있습니다.

참조 : [https://www.archunit.org/userguide/html/000_Index.html](https://www.archunit.org/userguide/html/000_Index.html)

참조 : [https://d2.naver.com/helloworld/9222129](https://d2.naver.com/helloworld/9222129)
