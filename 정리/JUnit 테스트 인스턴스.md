## JUnit 5: 테스트 인스턴스

**테스트 메소드마다 새로운 인스턴스를 만들어 실행한다.**

~~~java
//테스트 인스턴스
int value = 1;

@Test
@DisplayName("스터디 만들기 테스트 인스턴")
void testInstance() {
  System.out.println(this);
  System.out.println(value++);
}

@Test
@DisplayName("스터디 만들기 테스트 인스턴스 ")
void testInstance2() {
  System.out.println(this);
  System.out.println(value++);
}
~~~

- value 값을 1로 선언 후 각 테스트 메소드에서 **value를 증가하여 각각 2 또는 3** 이 출력될 거 같지만 결과는 다르다.
- 둘 다 1이라는 결과가 나타난다.
-  <img src="https://user-images.githubusercontent.com/40616436/74742335-bf3ed600-52a1-11ea-9df0-1492ffea82fc.png" alt="image" style="zoom:50%;" />



- **이유는 this를 통해 확인해보면, 각 메소드들의 인스턴스가 다르다는 것을 확인할 수 있다.**
-  <img src="https://user-images.githubusercontent.com/40616436/74742564-2a88a800-52a2-11ea-97e6-1bb0a24ef19c.png" alt="image" style="zoom:50%;" />
-  <img src="https://user-images.githubusercontent.com/40616436/74742687-5d32a080-52a2-11ea-975d-af9e5663f890.png" alt="image" style="zoom:50%;" />



**테스트 인스턴스를 하나만 만들어보자**

~~~java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyTest {
  @BeforeAll
  void beforeAll() {
    System.out.println("before all");
  }

  @AfterAll
  void afterAll() {
    System.out.println("after all");
  }
}
~~~

- 테스트 인스턴스를 하나만 만들기 위해서는 **@TestInstance(TestInstance.Lifecycle.PER_CLASS)** 를 클래스에 선언한다.
- 인스턴스가 하나만 만들어져 있기 때문에, **@BeforeAll이나 @AfterAll은 static으로 선언할 필요가 없다.**
  - 이유는, 한 클래스에 여러 테스트 인스턴스가 만들어지므로 단 하나의 메소드(static 메소드)에만 접근하기 위해 static으로 선언한 것이다.

- ![image](https://user-images.githubusercontent.com/40616436/74743301-7c7dfd80-52a3-11ea-8bde-b12ddbd714b8.png)
- ![image-20200218230839831](/Users/mesung/Library/Application Support/typora-user-images/image-20200218230839831.png)
  - **주소값이 동일하고 value값이 증가된 것을 확인할 수 있다.**



**참고**

**한 테스트 클래스 내에 있는 테스트 메소드들은 순서대로 실행되는 것이 아니다.**



