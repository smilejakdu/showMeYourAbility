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
