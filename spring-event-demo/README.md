# Spring event
Example Application For Spring Event Demo

## 개요
Event driven 을 위한 spring event 를 사용하는 프로젝트를 구성해보기 

## 후기
* 명시적으로 [ApplicationEventPublisher](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/ApplicationEventPublisher.html) 를 사용해서 publish 하는 것이 [Spring Domain Event](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/DomainEvents.html) 에 비해 코드상으로 관리하기는 좋아보임
* 개발자가 publish 를 신경 써야하는 단점이 있음  
* 도메인간 강결합을 끊기에 좋음

### Reference
* https://github.com/hibernate/hibernate-reactive
* http://hibernate.org/reactive/documentation/1.0/reference/html_single/
* https://spring.io/blog/2019/05/16/reactive-transactions-with-spring
