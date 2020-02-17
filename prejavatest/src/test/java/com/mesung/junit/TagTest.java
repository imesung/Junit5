package com.mesung.junit;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

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
