## Assertion

### **`assertEquals()`사용**

```java
assertEquals(StudyStatus.DRAFT, study.getStatus(), "Study 생성 시 처음 값은 DRAFT여야 한다");
//StudyStatus의 처음값과 study.getStatus() 값이 일치하는지 확인 
//세번째 파라미터에 메시지를 입력하면 오류 시 메시지가 호출되어 해당 메소드가 어떤 테스트에 해당하는지를 정확히 알 수 있다.
```

![image](https://user-images.githubusercontent.com/40616436/71778474-b47df980-2ff1-11ea-9dce-e4d6d1bafd9c.png)

**`assertEquals()`는 (내가 기대하는 값, 실제 나오는 값, 오류시 나타내려는 메시지)**



### **메시지를 나타낼 때 `Supplier`를 사용 가능하다.**

```java
assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
    public String get() {
        return "Study 생성 시 처음 값은 DRAFT여야 한다.";
    }
});
```

**그냥 String으로만 사용하면 되지 `Supplier`를 왜 사용하지?**

- `Supplier`를 사용하게 되면 오류가 발생한 순간에만 해당 String을 연산하여 나타낼 수 있다.
- 즉, `Supplier`를 사용하지 않으면 Test 메소드를 실행할 때마다 매번 호출되는 것이다.. 매우 불필요하다..
- 문자열 연산의 비용이 추가적으로 들수가 있으므로, 이런 비용이 아깝다고 생각하면 람다식으로 표현하는 것이 좋다. `Supplier`사용!



### `assertAll()` Assertion 정의한 메소드를 모두 확인하자

한 메소드에 `assert함수`를 여러개 선언 후 테스트를 진행할 시 상단 `assert함수`에서 오류가 발생하면 그 밑 함수들을 실행되지가 않는다.

```java
assertNotNull(study);
assertEquals(StudyStatus.DRAFT, study.getStatus(),
     () -> "Study 생성 시 처음 값은 DRAFT여야 한다.");	//여기에서 오류 시 밑에 실행이 안됨.
assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.");
```



**`assertAll()`을 사용해보자**

```java
assertAll(
    //Executable으로 람다식 활용
    () -> assertNotNull(study),
    () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                       () -> "Study 생성 시 처음 값은 DRAFT여야 한다."),
    () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
);
```

**`assertAll()`로 해당 함수들을 묶을 시 중간에 `assert함수`가 실행이 되지 않더라도 assert의 모든 함수를 실행할 수 있다.**



### `assertThrow()` 예외 발생을 확인

만약 사용자가 정의한 소스의 예외가 발생할 확률이 발생하게 된다면 이 또한, 테스트를 통해 확인할 수 있다.

```java
//Study class
public class Study{
    private int limit;
    public Study(int limit) {
        if(limit<0) {
            throw new IllegalArgumentException("limit은 0보다 커야한다.");
        }
        this.limit = limit;
    }
}

//Test
@Test
@DisplayName("스터디 만들기")
void create_new() {
    //발생한 예외 확인 가능
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Study(-10));
    
    //사용자가 기대한 예외 메시지가 맞는지 확인 가능
    String message = exception.getMessage();
    assertEquals("limit은 0보다 커야한다", exception.getMessage());	
}
```



### `assertTimeout()` 특정시간 안에 실행이 완료되었는지 확인

객체의 메소드를 처리하거나 클라이언트의 메시지를 처리하는 데 시간을 주고 특정 시간안에 처리가 완료되는 지 확인하는 함수이다.

```java
@Test
@DisplayName("스터디 만들기")
void create_new_time() {
    assertTimeout(Duration.ofMillis(100), () -> {
        new Study(10);
        Thread.sleep(300);
    });
}

```

- Study가 생성되고 Thread가 sleep되는 시간을 100밀리초 안에 실행이 되었는지 확인하는 것이다.

- 하지만 소스에서 보이듯이 `Thread.sleep(300)`으로 하여 이 함수는 실패하게 된다.

  ![image](https://user-images.githubusercontent.com/40616436/71780074-653fc500-3001-11ea-9153-62c0813ec833.png)

- 또한, Test Result를 보면 테스트를 실행한 시간이 찍혀있는 것을 확인할 수 있다.

  ![image](https://user-images.githubusercontent.com/40616436/71780100-a7690680-3001-11ea-87af-f96e4a6032ca.png) 



### 그 외 `Assert()`

- `assertNotNull()`

  - 값이 null이 아닌지 확인
  - `assertNotNull(study);`

- `assertTrue()`

  - 다음 조건이 참인지 확인
  - `assertTrue(boolean);`

- AssertJ 사용

  - `assertThat()`

  ```java
  Study st = new Study(10);
  assertThat(st.getLimit()).isGreaterThan(0);	//limit이 0보다 큰지 확인
  ```

  

- 