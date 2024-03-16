# showMeYourAbility

```agsl
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.datasource.url=jdbc:mysql://localhost:3306/show_me_your_ability?useSSL=false&serverTimezone=UTC
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.dialect.storage_engine=innodb
```
# 실행 port 확인

```agsl
lsof -i :13022
```

## swagger

http://localhost:13022/swagger-ui/index.html


# 기능별
### session 01
- 간단한 crud 구현
- swagger 적용

### session 02
- 최신순으로 5명의 선생님 출력
- 선생님의 이름을 입력받아 해당 선생님의 정보 출력
- 선생님의 이름을 입력받아 해당 선생님의 수업 리스트 중 최신순으로 페이지네이션 적용

### session 03
- refresh Token 적용
- 테스트 코드 안한거 작성

### session 04
- Apache Kafka 적용

# 스택별
### session 01

- 간단한 crud 구현
- swagger 적용
- JPA 적용

### session 02

- Query DSL 적용
- Redis 적용

### session 03

- 성능 테스트 
- Kafka 적용 