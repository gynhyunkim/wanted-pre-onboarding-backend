## 원티드 프리온보딩 인턴십 사전과제
---
### 기술 스택
- JAVA 11
- SpringBoot
- MySQL
### 구현 내용

- [x] 채용공고 등록
- [x] 채용공고 수정
- [ ] 채용공고 삭제
- [ ] 채용공고 목록 조회
- [ ] 채용공고 검색
- [ ] 채용 상세 페이지 조회
- [ ] 사용자의 채용공고 지원

### 테이블 설계

![erd](./resources/wanted_erd.PNG)

### API 명세
|name|http method|uri|request body|response body|
|---|---|---|---|---|
|채용공고 등록|post|/posting/create|{"company_id":company_id, "position":"backend developer", "reward":10000, "description":"~", "skill_set":"Python"}|{200, ok}|
|채용공고 수정|put|/posting/{postId}|{"position":"backend developer", "reward":10000, "description":"~", "skill_set":"Python"}||
|채용공고 삭제|delete|/posting/{postId}/delete|None||
|채용공고 목록 조회|get|/posting/all|None|[{"post_id":post_id, "company_name":company_name, "country":country, "region":region, "position": position, "reward": reward, "skill_set": skill_set}, {}...]||
|채용 상세 페이지|get|/posting/{postId}|None|{"post_id":post_id, "company_name":company_name, "country":country, "region":region, "position": position, "reward": reward, "skill_set": skill_set, "description":description}||


### 구현할 기능 세분화
1.채용공고 등록
- request body 유효성 검사
	- company_id는 유효한 값이어야 한다.
	- reward는 0 혹은 양수의 정수 값이어야 한다.
- job-posting 테이블에 data insert
- response 반환
2. 채용공고 수정
- path variable 유효성 검사
	- postId는 유효한 값이어야 한다.
- request body 유효성 검사
	- reward는 0 혹은 양수의 정수 값이어야 한다.
- job-posting 테이블에서 postId에 해당하는 데이터 update
- response 반환
4. 채용공고 삭제
- path variable 유효성 검사
	- postId는 유효한 값이어야 한다.
5. 채용공고 목록 조회
- 페이지네이션
6. 채용 상세 페이지 조회
- path variable 유효성 검사
	- postId는 유효한 값이어야 한다.

### 구현 과정
1. 채용 공고 등록 기능 구현
    - Company, JobPosting, User entity 구현
    - JobPostingRepository, CompanyRepository 생성
    - JobPostingService에서 JobPosting table에 값을 추가하는 메서드 구현
    - JobPostingController에 `/posting`으로 요청이 올 경우 처리하도록 구현
2. ExceptionHandler, custom ResponseEntity 구현
3. 채용 공고 수정 기능 구현
	- JPA의 dirty checking을 이용한 업데이트 구현