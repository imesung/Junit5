## JUnit 5 : 태깅과 필터링

각 테스트 그룹을 만들고, 원하는 테스트 그룹만 테스트를 진행할 수 있는 기능이다.

여러 조건으로 우리가 원하는 태그를 붙여 해당 태그를 지정하여 테스트를 진행할 수 있는 것이다.



**@Tag**

- 테스트 메소드에 태그를 추가할 수 있다.
- 하나의 테스트 메소드에 여러 태그를 사용할 수 있다.



**소스로 확인해보자**

Fast 태그와 slow 태그를 사용하여 용도에 맞게 테스트를 진행해보자

즉, 실행결과가 빠르게 나타나는 테스트를 fast, 실행결과가 느리게 나타나는 테스트는 slow로 가정하자

~~~java
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TagTest {
    @Test
    @DisplayName("스터디 만들기 fast")
    @Tag("fast")
    void create_new_study() {
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 slow")
    @Tag("slow")
    void create_new_study_again() {
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }
}
~~~



**테스트를 진행할 때 우리가 지정한 태그만 테스트를 진행해보자**

인텔리제이에서 Edit configuration에서 **Test Kind를 Tags로 변경해주고, Tag expression을 우리가 설정한 태그 이름으로 입력하면 원하는 태그만 테스트가 가능하다.**

![image](https://user-images.githubusercontent.com/40616436/74656328-41ab9500-51d1-11ea-887e-245bc8ea4222.png)



**터미널에서 빌드해보자**

~~~html
./mvnw test
~~~

해당 패키지(test)에 있는 모든 테스트를 진행할 수 있다.



**이제 각 서버에서 사용할 태그를 지정하여 터미널에서 빌드해보자**

여기서 중요한 점은, pom.xml에 profiles를 추가해야한다.

~~~xml
<profiles>
  <profile>
    <id>default</id>
    <activation>
      <activeByDefault>true</activeByDefault>
    </activation>
    <build>
      <plugins>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <configuration>
            <groups>fast</groups>
          </configuration>
        </plugin>
      </plugins>
    </build>
  </profile>
</profiles>
~~~

