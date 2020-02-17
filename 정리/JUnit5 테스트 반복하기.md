## JUnit : 테스트 반복하기

어떤 테스트에 대해서 반복적으로 테스트를 진행할 수 있다.

~~~java
@DisplayName("스터디 만들기")
@RepeatedTest(value = 10, name = "{displayName}, {currentRepetitions}/{totalRepetitions}" )
void create_study(RepetitionInfo repetitionInfo) {
  System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                     repetitionInfo.getTotalRepetitions());
}
~~~

- {displayName} : @DisplayName
- {currentRepetitions} : 현재 반복한 횟수
- {totalRepetitions} : 총 반복 횟수



**결과**

![image](https://user-images.githubusercontent.com/40616436/74663368-fac49c00-51de-11ea-9711-45119ec79804.png)



**다른 값들을 가지고 테스트를 반복적으로 진행하고 싶을 때**

~~~java
@DisplayName("스터디 만들기")
@ParameterizedTest(name = "{index} {displayName} message={0}")
@ValueSource(strings = {"내일은", "화요일", "입니다."})
void parameterizedTest(String messages) {
  System.out.println(messages);
}
~~~

- @ParameterizedTest : 테스트 결과값에 나타날 문장을 구사한다.
- @ValueSource : 각 메소드에서 사용할 값들을 지정해줄 수 있다.
  - 메소드에서 파라미터 값을 받아와 사용자가 원하는 로직으로 개발하며 테스트할 수 있다.

**결과**

![image](https://user-images.githubusercontent.com/40616436/74665115-5cd2d080-51e2-11ea-89a6-d14fd10d1451.png)

