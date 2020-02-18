## Mockito

Mock : 진짜 객체와 비슷하게 동작하지만, 프로그래머가 직접 그 객체의 행동을 관리하는 객체.

Mockito : Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법을 제공한다.



**DB, API 호출 시 API 및 DB를 Mock 객체로 만들고 각각의 행동들이 어떻게 동작할지에 대해 Mockito를 사용해서 코딩하며 테스트를 진행한다.** - 실제 API가 없거나 DAO나 Repository가 구현되어 있지 않아도 테스트가 가능하다.



**스프링 부트를 사용 시 2.2 버전 이후에는 spring-boot-starter-test에서 자동으로 Mockito를 추가해준다.**



**스프링 부트가 아닌 경우**

~~~xml
<!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.1.0</version>
    <scope>test</scope>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter -->
<!-- Junit에서 Mockito를 추가적으로 사용할 수 있음 -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>3.1.0</version>
    <scope>test</scope>
</dependency>

~~~



**다음 세가지만 알면 Mock을 활용한 테스트를 쉽게 작성할 수 있다.**

- Mock을 만드는 방법
- Mock이 어떻게 동작해야 하는지 관리하는 방법
- Mock이 어떻게 쓰이는지를 검증하는 방법

