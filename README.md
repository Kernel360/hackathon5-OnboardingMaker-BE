# 🚀 OnboardingMaker-BE

<img src="https://i.imgur.com/KY5jQ7x.png" alt="OnboardingMaker Logo" width="200" />

> **온보딩을 단순화하고, 해커톤 만큼 빠르게!**

---

## 📖 프로젝트 소개
`OnboardingMaker-BE`는 해커톤 5기 팀 프로젝트로, **사용자 맞춤형 온보딩 페이지**를 빠르고 간편하게 생성·관리할 수 있도록 도와주는 백엔드 서버입니다.

핵심 목표:
- 🧑‍💻 개발팀과 디자이너가 함께 협업하며 온보딩 콘텐츠를 즉시 배포
- ⚡ 빠른 API 응답으로 사용자 경험(UX) 최적화
- 🔒 보안 설정과 권한 관리를 통한 안전한 서비스 제공

---

## 🛠 기술 스택

| 분야          | 기술/라이브러리                                         |
|-------------|--------------------------------------------------|
| 언어          | Java 17                                          |
| 프레임워크     | Spring Boot 3.x                                  |
| 데이터베이스    | MySQL                                             |
| 보안          | Spring Security, JWT                             |
| 빌드 & 관리    | Gradle                                           |
| 캐시          | Redis                                            |
| 문서화        | Swagger/OpenAPI                                  |
| 배포          | AWS EC2, S3 (미리 서명된 URL)                    |
| CI/CD       | GitHub Actions                                   |

---

## 🚀 주요 기능

1. **유저 인증 & 권한 관리**
   - 세션 기반
  
2. **온보딩 콘텐츠 생성 & 관리**
   - 게시글(온보딩 카드) 생성 시 텍스트 입력 기능 제공
   - 각 게시글은 제목, 설명을 포함하며 미션 단위로 그룹화 가능
   - 그룹 수 설정 가능

2. **콘텐츠 섹션 구성**
   - 사용자가 원하는 구조로 그룹수를 자유롭게 지정할 수 있음

4. **댓글 시스템**
   - 게시글 단위로 사용자 간 소통을 위한 댓글 CRUD 기능 제공
   - 댓글 작성자 정보 및 작성 시간 표시
   - 대댓글 기능

4. **API 문서화**
   - Swagger UI 제공 (자동 갱신)
   - 프론트엔드와의 협업을 위한 직관적인 API 명세 제공

---

## 🔧 설치 및 실행 방법

1. 리포지토리 클론
   ```bash
   git clone https://github.com/kernel360/hackathon5-OnboardingMaker-BE.git
   cd hackathon5-OnboardingMaker-BE
   ```

2. 환경 변수 설정
   ```properties
   # .env 또는 application.yml
   SPRING_DATASOURCE_URL=jdbc:mysql://<HOST>:3306/<DB_NAME>
   SPRING_DATASOURCE_USERNAME=<USER>
   SPRING_DATASOURCE_PASSWORD=<PASSWORD>
   JWT_SECRET=<YOUR_SECRET>
   AWS_S3_BUCKET=<YOUR_BUCKET>
   AWS_ACCESS_KEY=<ACCESS_KEY>
   AWS_SECRET_KEY=<SECRET_KEY>
   ```

3. 빌드 & 실행
   ```bash
   ./gradlew clean build
   ./gradlew bootRun
   ```

4. Swagger UI 확인
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 📂 프로젝트 구조

```
com.example.onboarding
├─ config            # 스프링 설정 및 시큐리티
├─ controller        # API 요청 처리
├─ service           # 비즈니스 로직
├─ repository        # 데이터베이스 인터페이스
├─ domain            # 엔티티 정의
├─ dto               # 요청/응답용 객체
├─ util              # 유틸리티
└─ exception         # 예외 처리
```

---

## 📝 API 주요 엔드포인트

| 메서드 | 경로                           | 설명                          |
|------|------------------------------|-----------------------------|
| POST | `/api/auth/login`            | 로그인 (JWT 발급)               |
| POST | `/api/auth/logout`           | 로그아웃                       |
| GET  | `/api/users/me`              | 내 정보 조회                    |
| GET  | `/api/missions`              | 전체 미션 목록 조회               |
| POST | `/api/missions`              | 미션 생성                      |
| GET  | `/api/missions/{id}`         | 미션 상세 조회                   |
| POST | `/api/missions/{id}/replies` | 해당 미션에 댓글 작성            |
| GET  | `/api/missions/{id}/replies` | 해당 미션 댓글 목록 조회          |

---

## 🔒 보안 & 인증

- **Spring Security**로 모든 API 보호
- **Role 기반 권한** : `USER`, `ADMIN` 

---

