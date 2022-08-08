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

TestCode 작성시엔 @AutoWired로 DI를 시켜주세요.
-

@DataJpaTest : DB와 관련된 컴포넌트만 메모리에 로딩

