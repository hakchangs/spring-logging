# SPRING Embedded Tomcat LOGGING
1. ACCESS LOG
2. APPLICATION LOG

## 1. ACCESS LOG
임베디드 톰캣을 사용한다면, 간단히 ACCESS LOG 를 활성화할 수 있다.
```yaml
#application.yml
spring.tomcat.accesslog.enabled=true
```

## 2. APPLICATION LOG

요건사항
- http payload 를 로깅한다.
- 대상은 inbound / outbound
- 비동기 처리한다. --> `AsyncAppender` 로 사용하려전 Appender 를 wrapping 한다.
- 파일로 떨군다. --> `FileAppender`, `RollingFileAppender`
- rotate 하여 일정크기 이상이면 지운다. --> `RollingFileAppender`
- 일정 기간이 지나면 자동으로 삭제한다. --> `maxHistory` 로 기간설정 yyyy-MM-dd 인 경우 일단위로 적용 
- 파일 생성시 압축 --> `fileNamePattern` 맨뒤에 .gz 또는, .zip
- 최대 저장 사이즈 --> `totalSizeCap` 로 설정하며, 넘으면 오래된것부터 삭제

## REFS
### ACCESS LOG
- https://www.baeldung.com/spring-boot-embedded-tomcat-logs
### LOGBACK
- http://logback.qos.ch/
- https://howtodoinjava.com/spring-boot2/logging/multiple-log-files/
- https://yhzion.tistory.com/10
- https://docs.toast.com/ko/Analytics/Log%20&%20Crash%20Search/ko/logback-sdk-guide/
- https://romeoh.tistory.com/entry/Spring-Boot-Logback-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0
### AsyncAppender
- https://kangwoojin.github.io/programing/logback-async-appender/
- https://kwonnam.pe.kr/wiki/java/logback/asyncappender
### spring logging
- https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging
- https://www.baeldung.com/spring-boot-logging
### App LOG
- https://www.baeldung.com/spring-boot-add-filter
- https://jodu.tistory.com/49
