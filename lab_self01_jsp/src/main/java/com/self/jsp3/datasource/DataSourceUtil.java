package com.self.jsp3.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceUtil {

    // singleton 구현
    // DataSource는 여러개 있으면 안된다.
    // 그러기에 싱글톤으로 구현

    private static DataSourceUtil instance = null;

    private HikariDataSource ds;
    
    private DataSourceUtil() {
        HikariConfig config = new HikariConfig();
        
        config.setDriverClassName("oracle.jdbc.OracleDriver");
        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        config.setUsername("scott");
        config.setPassword("tiger");
        
        
        // 데이터 소스 객체 생성.
        ds = new HikariDataSource(config);
        
    }

    public static DataSourceUtil getInstance() {
        if (instance == null) {
            instance = new DataSourceUtil();
        }
        return instance;
    }

    public HikariDataSource getDataSource() {
        return ds;
    }
}
