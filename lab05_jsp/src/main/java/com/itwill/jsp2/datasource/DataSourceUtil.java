package com.itwill.jsp2.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// DAO와 DB(Oracle DB) 사이에서 미리 관계를 맺고 있는 DataSourceUtil 
// Connection을 DAO에게 보낸다.

public class DataSourceUtil {

    // singleton 구현 
    // DataSource는 여러개를 가지고 있으면 안된다.
    // 그러기에 싱글톤 패턴으로 구현
    private static DataSourceUtil instance = null;
    
    private HikariDataSource ds;
    
    private DataSourceUtil() {
        HikariConfig config = new HikariConfig(); // HikariCP 환경 설정 객체.
        
        // 커넥션 풀 환경 설정
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");
        
        // 데이터 소스 객체 생성
        ds = new HikariDataSource(config);
    };
    
    public static DataSourceUtil getInstance() {
        if(instance==null) {
            instance = new DataSourceUtil();
        } 
        return instance;
    }
    
    public HikariDataSource getDataSource() {
        return ds;
    }
    
    
}//DataSourceUtil

