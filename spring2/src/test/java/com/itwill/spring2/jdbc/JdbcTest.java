package com.itwill.spring2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleDriver;

@Slf4j // log4j-slf4j2를 이용한 로그 출력. -> Logger 타입 객체 생성.
@ExtendWith(SpringExtension.class) // Spring JUnit 테스트를 실행하는 메인 클래스
@ContextConfiguration(
        locations = {"file:/src/main/webapp/WEB-INF/application-context.xml"}
) // 스프링 컨텍스트(환경 변수) 파일의 경로(이름)
public class JdbcTest {

    @Test
    public void testOracleJdbc() throws SQLException {
        // JDBC 1. 라이브러리를 매니저에 등록.
        DriverManager.registerDriver(new OracleDriver());
        log.debug("Oracle_Driver 등록 성공");
        
        // JDBC 2. Connection 생성
        final String url = "jdbc:oracle:thin:@localhost:1521:xe";
        final String user = "scott";
        final String pwd = "tiger";
        
        Connection conn = DriverManager.getConnection(url,user,pwd);
        Assertions.assertNotNull(conn); // conn이 null이 아니면 테스트 성공
        log.debug("conn={}", conn);
        
        // JDBC 3. 사용했던 resource 해제
        conn.close();
        
        log.debug("Oracle 연결 해제 성공...!");
    }
    
}
