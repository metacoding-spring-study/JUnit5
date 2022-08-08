# JUnit5
테스트 진행순서

Repository => Service => Web

---
Controller : 클라이언트와 테스트(data 요청등)

Service : 기능들이 트랜잭션을 잘 타는지 확인

Repository : DB쪽 관련 테스트

단위 테스트 : 필요한 class 들만 메모리에 올려서 테스트

통합 테스트 : SpringBoot 와 동일한 환경에서 테스트(전부 메모리에 올림)

---

- TestCode 작성시엔 @AutoWired로 DI를 시켜주세요.

어노테이션 정리
-
@DataJpaTest : DB와 관련된 컴포넌트만 메모리에 로딩

@BeforeAll : 테스트 시작전에 한번만 실행

@BeforeEach : 각 테스트 시작전에 한번씩 실행
- 전체 Test Code가 아닌 Test Code 별로 트랜잭션이 돈다. 

# TestCode 3요소

given : 데이터 준비

stub : ??

when : 테스트 실행

then(verify) : 테스트 검증

---

create
-

1. Client가 data를 Controller로 request를 함
2. Controller가 받은 요청을 DTO class에 담아서 Service에게 넘김
3. Service는 전달받은 DTO객체를 Entity로 변환해서 Repository에게 전달
4. save() 메서드 호출해서 DB저장

DB저장 후
-

1. primary key 생성(id 생성완료)
2. save 메서드가 DB에 저장된 Entity를 return (db 데이터와 동기화된 데이터)

---

