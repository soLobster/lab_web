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
import com.itwill.jsp2.dto.UserSignInDto;
import com.zaxxer.hikari.HikariDataSource;

public class UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    private final HikariDataSource ds = DataSourceUtil.getInstance().getDataSource();

    // Singleton
    private static UserDao instance = null;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }// end of Singleton

    // 회원 가입시 필요한 SQL 문
    private static final String SQL_SIGN_UP = "insert into USERS (USERID, PASSWORD, EMAIL) values (?,?,?)";

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
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }

        return result;
    }// end insert(User user) method

    // 로그인 체크 SQL문
    private static final String SQL_SIGN_IN = "select * from USERS where USERID = ? and PASSWORD = ?";

    public User selectByUserIdAndPassword(UserSignInDto dto) {
        // TODO userid와 password가 일치하면 User 객체를, 일치하지 않으면 null을 리턴
        log.debug("selectByUserIdAndPassword(UserSignInDto = {})", dto);

        User user = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SIGN_IN);
            log.debug(SQL_SIGN_IN);
            stmt.setString(1, dto.getUserid());
            stmt.setString(2, dto.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                user = generateUserFromRS(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return user;
    }
    
    // 사용자가 포스트를 작성했을 때 포인트를 x 포인트를 지급하는 SQL 문장 
    private static final String SQL_UPDATE_POINT = 
            "update USERS set POINTS = POINTS + ? where USERID = ?";
    
    public int updatePoints(int point, String userid) {
        log.debug("updatePoints point= {}", point);
        log.debug("updatePoints userid= {}", userid);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        int result = 0;
        
        try {
            
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_POINT);
            log.debug(SQL_UPDATE_POINT);
            stmt.setInt(1, point);
            stmt.setString(2, userid);
            
            result = stmt.executeUpdate();
            log.debug("Point Update result = {}",result);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    

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
            e.printStackTrace();
        }

    }// end of closeResources(arg 3)

    private User generateUserFromRS(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        String userid = rs.getString("USERID");
        String password = rs.getString("PASSWORD");
        String email = rs.getString("EMAIL");
        Long points = rs.getLong("POINTS");

        return User.builder().id(id).userid(userid).password(password).email(email).points(points).build();
    }

    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }// end of closeResources(arg 2)

}// end of UserDao
