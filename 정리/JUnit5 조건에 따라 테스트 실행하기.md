## 조건에 따라 테스트 실행하기

**Assumptions**

- 특정한 OS, Java 버전, 환경변수에 따라 실행하거나 실행하지 말아야할 때 사용한다.



### 환경에 따라 테스트를 다르게 진행하자

- 메소드를 활용하자

  - **assumeTrue 메소드**

  ```java
  @Test
  @DisplayName("스터디 만들기")
  void create_new_method() {
      String test_env = System.getenv("TEST_ENV");
      System.out.println(test_env);
      assumeTrue("LOCAL".equalsIgnoreCase(test_env));
  }
  //LOCAL 환경 변수에서만 해당 테스트가 실행된다.
  ```

  - **assumingThat 메소드**

  ```java
  @Test
  @DisplayName("스터디 만들기")
  void create_new_method() {
      assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
          System.out.println("LOCAL 환경에서 테스트");
      });
  
      assumingThat("TEST".equalsIgnoreCase(test_env), () -> {
          System.out.println("TEST 환경에서 테스트");
      });
  }
  //LOCAL이거나 TEST 환경일 때 각각 다르게 테스트 가능하다.
  ```

- 어노테이션을 활용하자

```java
@Test
@DisplayName("스터디 만들기")
@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
void create_method6() {

}

@Test
@DisplayName("스터디 만들기")
@EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "test")
void create_method5() {

}
```



### OS에 따라 테스트를 다르게 진행하자.

```java
@Test
@DisplayName("스터디 만들기")
@EnabledOnOs({OS.MAC, OS.LINUX})
void create_method1() {

}

@Test
@DisplayName("스터디 만들기")
@DisabledOnOs({OS.MAC, OS.LINUX})
void create_method2() {

}
```

- **OS에 구분을 주어 테스트를 진행할 수 있다.**



### JAVA 버전에 따라 테스트를 다르게 진행하자.

```java
@Test
@DisplayName("스터디 만들기")
@EnabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10})
void create_method4() {

}

@Test
@DisplayName("스터디 만들기")
@DisabledOnJre({JRE.JAVA_8, JRE.JAVA_9, JRE.JAVA_10})
void create_method3() {

}
//Java 버전에 따라 테스트 다르게 실행가능하다.
```



### 환경에 따라 테스틀



