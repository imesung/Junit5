## JUnit5 : 커스텀 태그

JUnit에서 제공하는 어노테이션들을 메타 어노테이션으로 사용할 수 있다. 즉, 사용자가 만든 어노테이션에 JUnit 어노테이션을 넣어 사용할 수 있다는 것이다.



~~~java
//사용자 정의 어노테이션

//FastTest
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("fast")
public @interface FastTest {

}

//SlowTest
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("slow")
public @interface FastTest {

}


@FastTest
@DisplayName("스터디 만들기 fast")
void create_new_study_fast() {
  Study actual = new Study(100);
  assertThat(actual.getLimit()).isGreaterThan(0);
}

@SlowTest
@DisplayName("스터디 만들기 fast")
void create_new_study_slow() {
  Study actual = new Study(100);
  assertThat(actual.getLimit()).isGreaterThan(0);
}
~~~

이처럼 커스텀 태그를 사용하게 되면, @Tag("fast") 처럼 직접 입력을 하지 않고 정의한 어노테이션을 불러오므로, Type Safe할 수 있다는 장점이 있다.

