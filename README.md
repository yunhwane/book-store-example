# 🎯 나만의 플레이그라운드

자유롭게 만들고, 실험하고, 배우기 위한 개인 프로젝트입니다.

---

## 🛠️ 기술 스택 (Tech Stack)

- **언어 (Language)**: Kotlin
- **프레임워크 (Framework)**: Spring Boot 3.x
- **ORM**: JPA (Hibernate)
- **데이터베이스 (Database)**: MySQL
- **빌드 도구 (Build Tool)**: Gradle (Kotlin DSL)
- **문서화 (Documentation)**: Spring REST Docs
- **기본 키 (Primary Key)**: Snowflake 알고리즘 기반 ID 생성

---

## 🏛️ 헥사고날 아키텍처 (Hexagonal Architecture: 포트와 어댑터 패턴)

- **인바운드 포트 (Inbound Ports, UseCases)**: 시스템이 외부에 제공하는 기능 정의
- **아웃바운드 포트 (Outbound Ports, Persistence Ports)**: 시스템이 외부로부터 필요로 하는 기능 정의 (DB, 외부 API 등)
- **어댑터 (Adapters)**: 포트를 구현하여 실제 기술(JPA 등)을 연결하는 계층

---

# 📖 Book 테이블

## 📌 테이블 생성 쿼리 (DDL)

```sql
CREATE TABLE book (
    id BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(1000) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    category ENUM('FANTASY', 'SCIENCE_FICTION', 'MYSTERY', 'THRILLER') NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP()
);
```

### Table Structure
- id: Unique identifier for the book (Primary Key)
- title: Title of the book (up to 1000 characters)
- content: Content or description of the book (up to 1000 characters)
- category: Book category (ENUM type)
- created_at: Timestamp of creation (default: current time)
- updated_at: Timestamp updated automatically on modification


## index
- category와 id 컬럼에 대해 복합 인덱스를 생성하여, 카테고리 기반 책 조회 성능을 최적화하였습니다.
- 이 인덱스를 통해 특정 카테고리 내에서 id를 내림차순 정렬하여 최신 책을 빠르게 조회할 수 있습니다.

```sql
CREATE INDEX idx_category_id ON book (category ASC, id DESC);
```




