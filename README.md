# usermanager


```confi 
# Server
server.port=9000

# Jpa
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update

## PostgreSQL exemplo
# spring.datasource.url=jdbc:postgresql://localhost:5432/exemplo_db
# spring.datasource.username=
# spring.datasource.password=
# spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

## H2 exemplo
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Swagger OpenApi
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.operationsSorter=method


```