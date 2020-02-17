package com.mesung.junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

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

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
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