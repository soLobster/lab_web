package com.itwill.spring2.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log4j-slf4j2를 이용한 로그 출력. -> Logger 타입 객체 생성.
@ExtendWith(SpringExtension.class) // Spring JUnit 테스트를 실행하는 메인 클래스
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }) // 스프링 컨텍스트(환경 변수) 파일의
                                                                                              // 경로(이름)
public class HikariDataSourceTest {
    
    /*
     * 스프링에서 제일 중요한 개념.****** 의존성 주입 (DI: Dependecy Injection), 제어의 역전(IOC:
     * Inversion of Control) 전통적인 자바 개발 방법에서는 객체를 사용하는 곳에서 객체를 생성하고, 그 메서드(기능)을/를
     * 이용함.
     * 
     * 스프링 프레임워크에서는 스프링 컨테이너가 필요한 객체를 미리 메모리에 생성해 두고, 객체를 필요로 하는 곳에서는 변수 선언과 애너테이션만
     * 사용하면 스프링 컨테이너가 생성/관리하고 있는 빈(bean)을 필요한 곳에 주입해 줌. application-context.xml 파일에
     * 선언된 bean들을 스프링 컨테이너가 생성/관리함.
     */

    @Autowired // 스프링 컨테이너가 생성/관리하는 bean을 변수에 자동 주입.
    @Qualifier("hikariConfig") // bean들 중에서 아이디가 hikariConfig인 놈만 데려온다. (모호한 부분을 해결한다)
    private HikariConfig config;

    /*
     * hikariConfig: super type |__ hikariDataSource: sub type 다형성 때문에 hikariConfig
     * 타입으로 선언한 변수에는 hikariConfig 타입 객체와 hikariDataSource 타입 객체를 모두 주입할 수 있음. 컨텍스트
     * 파일에서 설정한 id값으로 특정 bean을 주입받을 때 Qualifier("id")를 사용.
     */

    @Autowired
    private HikariDataSource ds;

    @Autowired
    private SqlSessionFactoryBean session;
    
    @Test
    public void test() throws SQLException {
        Assertions.assertNotNull(config);
        log.debug("config = {}", config);

        // HikadriDataSource는 HikariConfig를 상속 받는다.
        // 따라서 명확하게 하지 않으면 스프링 컨테이너는 어떤 것을 인젝션 받을 지 모르기 때문에
        // 에러가 발생한다.

        Assertions.assertNotNull(ds);
        log.debug("ds = {}", ds);

        Assertions.assertNotNull(session);
        log.debug("session = {}", session);
        
        Connection conn = ds.getConnection(); // 데이터소스(Connection Pool)에서 커넥션을 빌려옴.
        Assertions.assertNotNull(conn);
        log.debug("Connection = {}", conn);
        
        conn.close(); // 사용했던 커넥션 객체를 pool에 반환.
        log.debug("커넥션 반환 성공..!");
    }

}
