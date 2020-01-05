## JUnit5 소개

### JUnit5 란

- 자바 개발자가 가장 많이 사용하는 테스팅 프레임 워크다.
- JUnit Platform에 의해서 Test를 실행해주고, TestEngine API의 구현체인 Jupiter를 활용하여 Test 소스를 작성할 수 있다.



### JUnit 5의 기본 애노테이션

- @Test
  - 테스트를 진행하고자 하는 메소드를 구현할 때 사용한다.
- @BeforeAll
  - @Test를 모두 실행하기 전에 딱 한번만 호출 되는 것이다.
- @AftereAll
  - @Test를 모두 실행한 후에 딱 한번만 호출 되는것이다.
- @BeforeEach
  - @Test를 모두 실행할 때 각각의 @Test가 실행되기 전에 호출되는 것이다.
- @AfterEach
  - @Test를 모두 실행할 때 각각의 @Test가 실행된 후에 호출되는 것이다.
- @Disabled
  - 테스트를 진행하지 않은 @Test가 있을 시 해당 애노테이션을 붙이면 테스트를 진행하지 않을 수 있다.
  - 현재 운영에 사용되어지지는 않은데 추후 사용될 수도 있으니! 지우지 않고 냅두는 상황이 매우 많다.

```java
class StudyTest {

    @Test
    void create1() {
        System.out.println("create1");
    }

    @Test
    @Disabled
    //테스트 하는 것중 테스트 하지 않은 것을 빼려고 할 때 사용
    void create2() {
        System.out.println("create2");
    }

    //@Test를 모두 실행하기 전에 딱 한 번만 실해오디는 것
    //private은 안되고 default만 허용, return type이 있으면 안됨
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    //모든 Test가 실행된 후 딱 한번만 호출
    //static void로 구현
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    //모든 테스트를 실행할 때 각각의 테스트가 실행되기 이전에 호출
    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    //모든 테스트를 실행할 때 각각의 테스트가 실행되기 이후에 호출
    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }

}
```

**실행 결과**

![image](https://user-images.githubusercontent.com/40616436/71777996-2737a680-2feb-11ea-80da-34c08dfac7e4.png)



### JUnit5 Test 메소드를 작성할 때 주의

Test Class나 메소드를 작성할 때 접근제한자를 붙여줘야 했는데, 그럴 필요가 없어졌다. 이유는 **리플렉션**을 사용하기 때문이다.

**리플렉션**을 사용할 시 private이든 default든 접근이 가능하니깐 굳이 접근제한자를 붙일 필요가 없다라고 판단한 것이다.