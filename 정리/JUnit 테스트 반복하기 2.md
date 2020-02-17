## JUnit 5 : 테스트 반복하기 2부

**인자 값들의 소스**

- @ValueSource

  - 파라미터를 나열한다.

- @NullSource, @EmptySource, @NullAndEmptySource

  - 기존 파라미터에 **Null** 혹은 **빈값**의 파라미터를 추가할 수 있다.

    - <img src="https://user-images.githubusercontent.com/40616436/74667141-213a0580-51e6-11ea-806c-d2e659dd50a9.png" alt="image" style="zoom:50%;" />

    

    - <img src="https://user-images.githubusercontent.com/40616436/74667660-05832f00-51e7-11ea-9cdf-35662157615b.png" alt="image" style="zoom:50%;" 	/>

- @EnumSource

- @MethodSource

- @CsvSource

- @CsvFileSource

- @ArgumentSource



**인자 값 타입 변환**

- 암묵적인 타입 변환

- 명시적인 타입 변환

  - @ValueSource로 선언한 파라미터(@ValueSource(ints {10, 20, 40}))를 객체의 생성자에 파라미터로 하여 객체를 생성한 후 객체로 접근하여 가져온 값들을 테스트에서 확인해보자.

    - **즉, 커스텀한 타입으로 변환하여 파라미터를 받고자 할 땐, Converter를 사용한다.**

  - **SimpleArgumentConverter** 상속 받은 구현체를 이용한다.

  - **@ConverWith**

  - **SimpleArgumentConverter는 하나의 인자값만 가능하다**

  - Ex.

    - ~~~java
      //설정한 파라미터로 객체 생성 후 객체에서 해당 값들을 가져와 테스트 진행
      @DisplayName("스터디 만들기")
      @ParameterizedTest(name = "{index} {displayName} message={0}")
      @ValueSource(ints = {10, 20, 40})
      //@ConvertWith를 활용하여 해당 파라미터로 생성한 Study 객체를 받을 수가 있다.
      void parameterized(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
      }
      
      //SimpleArgumentConverter를 상속받아 구현(new Study(각 파라미터))
      static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
          assertEquals(Study.class, targetType, "Can only convert to Study");
          return new Study(Integer.parseInt(source.toString()));
        }
      }
      ~~~

    - <img src="https://user-images.githubusercontent.com/40616436/74668518-bc33df00-51e8-11ea-9495-1032a7220803.png" alt="image" style="zoom:50%;" />

**인자 값 조합**

- ArgumentsAccessor

- 커스텀 Accessor

  - ArgumentsAggregator 인터페이스 구현

  - @AggregateWith

  - **ArgumentsAggregator는 두개 이상의 인자값을 받을 수 있다.**

  - Ex.

    - ~~~java
      //일반 파라미터 사용
      @DisplayName("스터디 만들기")
      @ParameterizedTest(name = "{index} {displayName} message={0}")
      @CsvSource({"10, 'java study'", "20, spring"})
      void parameterizedTest_study(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
      }
      
      //ArgumentsAccessor 사용
      @DisplayName("스터디 만들기")
      @ParameterizedTest(name = "{index} {displayName} message={0}")
      @CsvSource({"10, 'java study'", "20, spring"})
      void parameterizedTest_study(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
      }
      
      //@AggregateWith 사용
      @DisplayName("스터디 만들기")
      @ParameterizedTest(name = "{index} {displayName} message={0}")
      @CsvSource({"10, 'java study'", "20, spring"})
      void parameterizedTest_study_again(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
      }
      
      static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
          return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
      }
      ~~~

    - <img src="https://user-images.githubusercontent.com/40616436/74669315-42045a00-51ea-11ea-963b-2d32d5eb8195.png" alt="image" style="zoom:50%;" />

  - **ArgumentsAggregator의 제약조건**

    - 반드시 static inner class 거나 public class 여야 한다.