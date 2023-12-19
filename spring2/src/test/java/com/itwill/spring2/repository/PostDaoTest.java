package com.itwill.spring2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.itwill.spring2.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log4j-slf4j2를 이용한 로그 출력. -> Logger 타입 객체 생성.
@ExtendWith(SpringExtension.class) // Spring JUnit 테스트를 실행하는 메인 클래스
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/application-context.xml" }) // 스프링 컨텍스트(환경 변수) 파일의 경로(이름)                                                              
public class PostDaoTest {

    // PostDao 타입 객체를 주입.
    @Autowired
    private PostDao postDao;
    
    //@Test
    public void selectTest() {
        Assertions.assertNotNull(postDao);
        
        List<Post> list = postDao.selectOrderByIdDesc();
        log.debug("list size = {}", list.size());
        if(list.size() > 0) {
            log.debug(list.get(0).toString());
        }
        
    }//@Test selectTest()
    
    //@Test
    public void selectByIdTest() {
        Post p = postDao.selectById(1);
        Assertions.assertNotNull(p);
        log.debug("p={}",p);
        
        p = postDao.selectById(1_000); // 테이블에 존재하지 않는 아이디로 검색 했을 때
    }
    
    @Test
    public void insertTest() {
        Post post = Post.builder()
                .title("INSERT_MyBatis_TEST")
                .content("23.11.29 MyBatis Test")
                .author("admin")
                .original_file("/Users/ojng/lab_web/spring2/src/main/webapp/static/test.png")
                .build();
        
        int result = postDao.insert(post);
        Assertions.assertEquals(1, result);
    }
    
    //@Test
    public void updateTest() {
                
        Post post = Post.builder().title("Upadate test").content("Update test 42L").id(42L).build();
        
        int result = postDao.update(post); // 아이디가 존재하는 경우 업데이트 성공
        Assertions.assertEquals(1, result); 
        
        post = Post.builder().title("").content("").id(1_000L).build();
        result = postDao.update(post); // 아이디가 존재하지 않는 경우 업데이트 실패.
        Assertions.assertEquals(1, result); 
        
    }
    
    //@Test
    public void deleteTest() {
        
        int result = postDao.delete(2L); // 아이디가 존재하는 경우 삭제 성공
        Assertions.assertEquals(1, result);
        
//        int failResult = postDao.delete(100); // 아이디가 존재하지 않는 경우 삭제 실패.
//        Assertions.assertEquals(1, failResult);
        
    }
    
}//PostDaoTest
