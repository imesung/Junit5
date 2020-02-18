package com.mesung.study;

import com.mesung.domain.Member;
import com.mesung.domain.Study;
import com.mesung.member.MemberNotFoundException;
import com.mesung.member.MemberService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /*
    //3. 인스턴스 변수로 만들어보자
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;
    */

    @Test
    //4. 파라미터에 @Mock을 직접 넣자
    void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        //2. Mock을 사용해서 긴 것을 줄여보자
        //MemberService memberService = mock(MemberService.class);
        //StudyRepository studyRepository = mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        /*
        //1. default method로 선언
        MemberService memberService = new MemberService() {
            @Override
            public Optional<Member> findById(Long memberId) throws MemberNotFoundException {
                return Optional.empty();
            }
        };

        StudyRepository studyRepository = new StudyRepository() {
            @Override
            public List<Study> findAll() {
                return null;
            }

            @Override
            public List<Study> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Study> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends Study> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Study> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<Study> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Study getOne(Long aLong) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Study> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Study> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> S save(S s) {
                return null;
            }

            @Override
            public Optional<Study> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Study study) {

            }

            @Override
            public void deleteAll(Iterable<? extends Study> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Study> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Study> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Study> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Study> boolean exists(Example<S> example) {
                return false;
            }
        };
*/
    }

}