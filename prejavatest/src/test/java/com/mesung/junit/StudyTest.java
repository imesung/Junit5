package com.mesung.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {

    @Test
    void create() {
        Study study = new Study();
        assertNotNull(study);
        System.out.println("create");
    }

    @Test
    void create1() {
        System.out.println("create1");
    }

    @Test
    @Disabled
    //테스트 하는 것중 테스트 하지 않은 것을 빼려고 할 때 사용
    void create2() {
        System.out.println("create2");
    }

    //@Test를 모두 실행하기 전에 딱 한 번만 실해오디는 것
    //private은 안되고 default만 허용, return type이 있으면 안됨
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    //모든 Test가 실행된 후 딱 한번만 호출
    //static void로 구현
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    //모든 테스트를 실행할 때 각각의 테스트가 실행되기 이전에 호출
    @BeforeEach
    void beforeEach(){
        System.out.println("before each");
    }

    //모든 테스트를 실행할 때 각각의 테스트가 실행되기 이후에 호출
    @AfterEach
    void afterEach(){
        System.out.println("after each");
    }

}