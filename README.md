### Arquitetura

A aplicação foi desenvolvida em [Spring boot](https://projects.spring.io/spring-boot/) usando alguns dos seus starters (WEB, MONGO), os dados estão sendo salvos usando o [Mongodb](https://www.mongodb.com), os testes foram desenvolvidos usando a nova versão do [JUnit](http://junit.org/junit5/) e a documentação para uso das api's foi utilizado o [Swagger](https://swagger.io/).

### Subindo a aplicação

```
mvn spring-boot:run
```

### Subindo a aplicação com Docker
```
mvn clean package docker:build
```
```
docker-compose up
```

Acesse o swagger para usar as api's http://localhost:8080/swagger-ui.html.