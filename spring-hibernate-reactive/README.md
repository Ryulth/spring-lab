# Spring Hibernate Reactive
## 개요
JPA 대표적인 구현체 Hibernate의 Reactive 프로젝트를 Spring 에 적용해보기 

## 후기
* 가장 궁금했던 Hibernate 의 1차 캐시는 트랜잭션 내에서 잘 작동함
* 2021-05-20 기준 `ReactiveTransactionManager` 의 `Hibernate` 용 구현체는 없음 따라서 `@Transactional` 을 사용하기 위한 `TransactionManager` 을 등록할 수 없는 상태
* 2021-05-20 기준 1차 캐시 기능이 없지만 스프링 트랜잭션을 적용할 수 있는 [spring-r2dbc](https://spring.io/projects/spring-data-r2dbc)를 사용하는 것이 좋아보임

### Reference
* https://github.com/hibernate/hibernate-reactive
* http://hibernate.org/reactive/documentation/1.0/reference/html_single/
* https://spring.io/blog/2019/05/16/reactive-transactions-with-spring
