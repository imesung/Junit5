## JUnit5 테스트 이름 표기하기

**테스트를 실행하게 되면 `Test Results`에  @Test 메소드명이 찍히게 된다.**

![image](https://user-images.githubusercontent.com/40616436/71778112-b0031200-2fec-11ea-9536-57ed32451456.png)

애노테이션을 활용하여 테스트 이름을 변경시킬 수가 있다.

- @DisplayNameGeneration
- @DisplayName



### @DisplayNameGeneration

클래스에 해당 애노테이션을 붙이면 @Test 메소드 이름에 `_`로 표시한 모든 부분은 space로 처리된다.

```java
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {
}
```

![image](https://user-images.githubusercontent.com/40616436/71778130-14be6c80-2fed-11ea-9406-94ebb543b98f.png)

**클래스와 메소드에서 사용가능하다.**



### @DisplayName

@Test 메소드에 해당 애노테이션을 붙이면 사용자가 정의한 이름으로 테스트 이름을 표기할 수 있다.

```java
@Test
@DisplayName("테스트 스터디 하기 \uD83D\uDE31")
void create_new_study() {
    Study study = new Study();
    assertNotNull(study);
    System.out.println("create");
}

@Test
@DisplayName("테스트 스터디 하기 :)")
void create1_new_study_again() {
    System.out.println("create1");
}
```

![1578218403731](https://user-images.githubusercontent.com/40616436/71778225-46840300-2fee-11ea-96ff-c2df676da5ff.png)

**@DisplayNameGeneration 보다 우선 순위가 높다.**

