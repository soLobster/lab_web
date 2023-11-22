package com.itwill.jsp2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.datasource.DataSourceUtil;
import com.itwill.jsp2.domain.Post;
import com.zaxxer.hikari.HikariDataSource;

import oracle.jdbc.proxy.annotation.Pre;

// MVC 아키텍쳐에서 영속성(persistance) 계층을 담당하는 클래스
// DB CRUD
// DAO(Data Access Object)
public class PostDao {

    // slf4j의 로깅 기능 사용:
    private static final Logger log = LoggerFactory.getLogger(PostDao.class);

    // singleton 구현
    private static PostDao instance = null;

    private HikariDataSource ds;

    private PostDao() {
        ds = DataSourceUtil.getInstance().getDataSource();
    }

    public static PostDao getInstance() {
        if (instance == null) {
            instance = new PostDao();
        }

        return instance;
    }

    // POSTS 테이블의 전체 레코드를 ID의 내림차순 정렬로 검색.

    private static final String SQL_SELECT = "select * from posts order by ID desc";

    // SQL_SELECT를 실행하는 메서드:
    public List<Post> select() {
        List<Post> list = new ArrayList<Post>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();

//            DataSource 에서 커넥션 객체를 빌려옴
//            config.setDriverClassName("oracle.jdbc.OracleDriver");
//            config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
//            config.setUsername("scott");
//            config.setPassword("tiger");

            stmt = conn.prepareStatement(SQL_SELECT);
            log.debug(SQL_SELECT);

            rs = stmt.executeQuery();

            while (rs.next()) { // ResultSet 현재 위치에 검색 레코드가 있으면

//                Long id = rs.getLong("ID");
//                String title = rs.getString("TITLE");
//                String content = rs.getString("CONTENT");
//                String author = rs.getString("AUTHOR");
//                Timestamp created = rs.getTimestamp("CREATED_TIME");
//                Timestamp modified = rs.getTimestamp("MODIFIED_TIME");

                Post post = generatePostFromRS(rs);

                list.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return list;
    }

    // POSTS 테이블의 레코드를 입력된 검색어로 검색.
    
    private static final String SQL_SELECT_BY_TITLE = 
            "select * from POSTS where upper(TITLE) like ? order by ID desc";
    private static final String SQL_SELECT_BY_CONTENT = 
            "select * from posts where upper(content) like ? order by id desc";
    private static final String SQL_SELECT_BY_TC = 
            "select * from posts where upper(title) like ? or upper(content) like ? order by id desc";
    private static final String SQL_SELECT_BY_AUTHOR = 
            "select * from posts where upper(author) like ? order by id desc";
    
    
    public List<Post> search(String value, String keyword) {
        final String searchKeyword = "%" + keyword.toUpperCase() + "%";
        List<Post> result = new ArrayList<Post>();        
        log.debug("search (value={})", value);
        log.debug("search (searchkeyword={})", searchKeyword);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ds.getConnection();
            
            switch (value){
            case "t":
                stmt = conn.prepareStatement(SQL_SELECT_BY_TITLE);
                stmt.setString(1, searchKeyword);
                break;
            case "c":
                stmt = conn.prepareStatement(SQL_SELECT_BY_CONTENT);
                stmt.setString(1, searchKeyword);
                break;
            case "tc":
                stmt = conn.prepareStatement(SQL_SELECT_BY_TC);
                stmt.setString(1, searchKeyword);
                stmt.setString(2, searchKeyword);
                break;
            case "a":
                stmt = conn.prepareStatement(SQL_SELECT_BY_AUTHOR);
                stmt.setString(1, searchKeyword);
                break;
            }
            
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Post post = generatePostFromRS(rs);
                result.add(post);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return result;
    }
    
    // 새 포스트 작성에서 사용되는 SQL 문장
    private static final String SQL_INSERT = "insert into POSTS (TITLE, CONTENT, AUTHOR) values(?,?,?)";

    // SQL_INSERT를 실행하는 메서드
    public int insert(Post post) {
        log.debug("insert(post={})", post);
        int result = 0;

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            log.debug(SQL_INSERT);

            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setString(3, post.getAuthor());

            result = stmt.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        return result;
    }
    
    // POSTS 테이블 Update 하기
    private static final String SQL_UPDATE_BY_ID = 
            "update POSTS set TITLE = ? , CONTENT = ? , MODIFIED_TIME = systimestamp where ID = ?";
    
    // SQL_UPDATE_BY_ID를 실행하는 메서드
    public int update(Post post) {
        log.debug("update(post={})", post);
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_BY_ID);
            log.debug(SQL_UPDATE_BY_ID);
            
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getContent());
            stmt.setLong(3, post.getId());
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    

    // POSTS 테이블에서 아이디(PK)로 검색하기
    private static final String SQL_SELECT_BY_ID = "select * from POSTS where ID = ?";

    // SQL_SELCT_BY_ID 문장을 실행하고 결과를 처리하는 메서드.
    public Post select(Long id) {
        log.debug("select(id={})", id);

        Post post = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            log.debug(SQL_SELECT_BY_ID);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();
            
            if (rs.next()) {
                // ResultSet에서 Post 객체를 만듦.
                post = generatePostFromRS(rs);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return post;
    }

    // 포스트 아이디(PK)로 포스트 삭제하기
    private static final String SQL_DELETE_BY_ID = 
            "delete from POSTS where ID = ?";
    
    public int delete(Long id) {
        log.debug("delete(id={})",id);
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;
        
        try {
            
            conn = ds.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_BY_ID);
            log.debug(SQL_DELETE_BY_ID);
            stmt.setLong(1, id);
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt);
        }
        
        return result;
    }
    
    private Post generatePostFromRS(ResultSet rs) throws SQLException {

        Long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        String content = rs.getString("CONTENT");
        String author = rs.getString("AUTHOR");
        Timestamp created = rs.getTimestamp("CREATED_TIME");
        Timestamp modified = rs.getTimestamp("MODIFIED_TIME");

        Post post = new Post(id, title, content, author, created, modified);

        return post;
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
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void closeResources(Connection conn, Statement stmt) {
        closeResources(conn, stmt, null);
    }

}
