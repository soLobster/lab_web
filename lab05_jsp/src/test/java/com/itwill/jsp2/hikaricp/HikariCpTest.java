package com.itwill.jsp2.hikaricp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCpTest {
    private static final Logger log = LoggerFactory.getLogger(HikariCpTest.class);
    
    @Test
    public void test() {
        // HikariCP(커넥션 풀) 환경 설정을 위한 객체 생성:
        HikariConfig config = new HikariConfig();
        
        // HikariCP 환경 설정:
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        
        config.setJdbcUrl("jdbc:oracle:this:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");
        
        // 환경 설정 내용이 적용된 커넥션 풀 객체를 생성:
        HikariDataSource ds = new HikariDataSource(config); //ds -> dataSource
        
        // 커넥션 풀(데이터 소스) 객체는 null이 아니어야 한다.
        Assertions.assertNotNull(ds);
        log.debug("ds={}", ds);
        
    }
}
