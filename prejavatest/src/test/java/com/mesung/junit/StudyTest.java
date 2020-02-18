package com.mesung.junit;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Empty;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudyTest {

    //테스트 인스턴스
    @BeforeAll
    void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    void afterAll() {
        System.out.println("after all");
    }

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


    //테스트 반복하기
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

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java study'", "20, spring"})
    void parameterizedTest_study(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterized(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("스터디 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"내일은", "화요일", "입니다."})
    @EmptySource
    void parameterizedTest(String messages) {
        System.out.println(messages);
    }




    @DisplayName("스터디 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}" )
    void create_study(RepetitionInfo repetitionInfo) {
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
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

/*    @Test
    @DisplayName("스터디 만들기 fast")
    @Tag("fast")
    void create_new_study_fast() {
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("스터디 만들기 slow")
    @Tag("slow")
    void create_new_study_slow() {
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }*/

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

    @Test
    @DisplayName("스터디 만들기")
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void create_method2() {

    }

    @Test
    @DisplayName("스터디 만들기")
    @DisabledOnOs({OS.MAC, OS.LINUX})
    void create_method1() {

    }

    @Test
    @DisplayName("스터디 만들기")
    void create_new_method() {
        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(test_env));

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("LOCAL 환경에서 테스트");
        });

        assumingThat("TEST".equalsIgnoreCase(test_env), () -> {
            System.out.println("TEST 환경에서 테스트");
        });

        Study study = new Study(-10);
    }

    @Test
    @DisplayName("스터디 만들기")
    void create_new_time() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
    }

    @Test
    @DisplayName("스터디 만들기")
    void create_new() {
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야한다.", exception.getMessage());
    }

    @Test
    @DisplayName("테스트 스터디 하기 \uD83D\uDE31")
    void create_new_study() {
        assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        Study study = new Study(-10);

        assertAll(
                //Executable으로 람다식 활용
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(),
                        () -> "Study 생성 시 처음 값은 DRAFT여야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야한다.")
        );

    }

    @Test
    @DisplayName("테스트 스터디 하기 :)")
    void create1_new_study_again() {
        System.out.println("create1");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }
}