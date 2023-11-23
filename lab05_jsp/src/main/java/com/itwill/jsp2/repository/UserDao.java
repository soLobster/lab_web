package com.itwill.jsp2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasource.DataSourceUtil;
import com.itwill.jsp2.domain.User;
import com.zaxxer.hikari.HikariDataSource;

public class UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

    // Singleton
    private static UserDao instance = null;

    private UserDao() {}

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }// end of Singleton

    // 회원 가입시 필요한 SQL 문
    private static final String SQL_SIGN_UP = 
            "insert into USERS (USERID, PASSWORD, EMAIL) values (?,?,?)";

    // 회원 가입 메서드 인서트된 행의 갯수를 리턴하겠다.
    public int insert(User user) {
        log.debug("insert(User={})", user);
        int result = 0;

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SIGN_UP);

            stmt.setString(1, user.getUserid());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());

            result = stmt.executeUpdate(); // insert update delete => executeUpdate
                                           // select executeQuery

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            closeResources(conn, stmt);
        }

        return result;
    }// end insert(User user) method

    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }// end of closeResources(arg 3)

    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }// end of closeResources(arg 2)

}// end of UserDao
