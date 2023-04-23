# Todo API Server Application

Todo Server는 할 일 관리 웹 애플리케이션을 위한 RESTful API 서버 프로젝트 입니다

## 주요기능
- 할 일 추가, 삭제, 수정, 조회
- 할 일의 상태 변경
- 할 일 검색 기능
- 기한별 할 일 관리

### API 스펙

| Method | Endpoint                 | Request Body                                                                                 | Response Body                                                                                                                                                                                                                  | Description                             |
|--------|--------------------------|----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------|
| POST   | `/tasks`                 | `{ "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01" }`              | `{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"}`                                                                                                                       | 새로운 할 일 생성                              |
| GET    | `/tasks`                 | `dueDate` (optional)                                                                         | `[{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"}, {"id": 2, "title": "Task 2", "description": "Do something else", "dueDate": "2023-05-01", "status": "IN_PROGRESS"}]` | 모든 할 일 조회(마감일이 있을 경우, 마감일에 해당하는 할 일 조회) |
| GET    | `/tasks/{id}`            | N/A                                                                                          | `{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"}`                                                                                                                       | 특정 ID 에 해당하는 할 일 조회                     |
| GET    | `/tasks/status/{status}` | N/A                                                                                          | `[{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "TODO"}, {"id": 3, "title": "Task 3", "description": "Do something else", "dueDate": "2023-05-02", "status": "TODO"}]`        | 특정 상태에 해당하는 할 일 모두 조회                   |
| PUT    | `/tasks/{id}`            | `{ "title": "Updated Task 1", "description": "Do something else", "dueDate": "2023-05-02" }` | `{"id": 1, "title": "Updated Task 1", "description": "Do something else", "dueDate": "2023-05-02", "status": "TODO"}`                                                                                                          | 특정 ID 에 해당하는 할 일 수정                     |
| PATCH  | `/tasks/{id}/status`     | `{ "status": "IN_PROGRESS" }`                                                                | `{"id": 1, "title": "Task 1", "description": "Do something", "dueDate": "2023-05-01", "status": "IN_PROGRESS"}`                                                                                                                | 특정 ID 에 해당하는 할 일의 상태 변경                 |
| DELETE | `/tasks/{id}`            | N/A                                                                                          | `{ "success": true }`                                                                                                                                                                                                          | 특정 ID 에 해당하는 할 일 삭제                     |

### 리소스 표현

- 할 일 (Task):
    - id: `Long`
    - title: `String`
    - description: `String`
    - dueDate: `LocalDate`
    - status: `TaskStatus`

- 할 일 상태 (TaskStatus): Enum
  - `TODO`: 할 일이 등록되었지만 아직 작업이 시작되지 않은 상태
  - `IN_PROGRESS`: 현재 진행 중인 상태 
  - `ON_HOLD`: 작업이 일시 중단된 상태
  - `COMPLETED`: 작업이 완료된 상태
  - `CANCELLED`: 작업이 취소된 상태로 더 이상 진행하지 않음

## 시작하기

### 필요 도구
- JDK 17 이상
- Gradle 7.3 이상 

### 개발환경
- Spring Boot 3.0.4
- H2 인메모리 데이터베이스
- JPA
- Lombok

### 테스트환경
- Spring Boot Test
- Mockito
- JUnit 5

### 설치 및 실행
1. 레포지토리를 클론하고 프로젝트 디렉토리로 이동합니다 
```sh
$> git clone https://github.com/graceForClass/todo-server.git
$> cd todo-server   # 프로젝트 디렉토리로 이동
```
2. gradle 로 프로젝트를 빌드합니다
```sh
$> gradle clean build
```
3. JAR 파일을 실행합니다
```sh
$> java -jar build/libs/todo-server-1.0-SNAPSHOT.jar
```

## 라이센스
이 프로젝트는 [MIT 라이센스](./LICENSE)에 따라 사용할 수 있습니다