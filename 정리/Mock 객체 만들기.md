## Mock 객체 만들기

**Mock 객체는 언제 만들까?**

1. Service에서 인터페이스를 참조해서 사용하며 로직을 구성할 때는 **해당 인터페이스에 있는 메소드들을 가져와 로직을 구성하고자 한다.**
2. 그러나 **인터페이스의 구현체는 따로 구현되어 있지 않다.**
3. 이럴 때, **내가 인터페이스가 구현되어 있다는 가정하에 구성한 로직을 실행하고자 할 때 Mock 객체를 만들어 테스트 한다.**
4. 이 때, **Mock 객체는 참조하는 인터페이스**가 되는 것이다.



**Mock 객체를 만들어보자**

~~~java
@Test
void createStudyService() {

  //1. memberService와 studyRepository를 파라미터로 받아 우리가 구성한 service를 테스트해보려고 하는데,
  // memberService와 studyRepository는 현재 구현체가 존재하지 않는다.
	StudyService studyService = new StudyService(memberService, studyRepository);
  
  //2. default method로 구현..
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

    ...

  };
  //////////////////////////////////////////////////////////////////////////////////////
  
  
  //3. 2번의 코드를 Mock을 사용해서 긴 것을 줄여보자 - Mockito를 사용하여 가짜 객체를 만든다.
  MemberService memberService = mock(MemberService.class);
  StudyRepository studyRepository = mock(StudyRepository.class);

  //4. 우리가 원하는 테스트를 진행할 수 있다.
  StudyService studyService = new StudyService(memberService, studyRepository);
  assertNotNull(studyService);
  
}


//////////////////////////////////////////////////////////////////////////////////////
//5. 인스턴스 변수로 선언해보자
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;

  @Test
  void createStudyService() {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
  }
  
  
  //////////////////////////////////////////////////////////////////////////////////////
	//6. 해당 메소드에서만 @Mock을 사용하자
  @Test
  void createStudyService(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
}
~~~

