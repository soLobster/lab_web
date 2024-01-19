package com.itwill.springboot4.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostQuerydslTest {

    @Autowired
    private PostRepository postDao;
    
    //@Test
    public void test() {
        Assertions.assertNotNull(postDao);
        
        Post entity = postDao.searchById(316L);
        
        log.info("entity = {}", entity);
    }
    
    //@Test
    public void test2() {
        List<Post> list = postDao.searchByTitleOrContent("tESt");
        
        list.forEach((x) -> log.info((x).toString()));
    }
    
    //@Test
    public void testModifiedTime() {
//        LocalDateTime from = LocalDateTime.of(2024, 1, 19, 0, 0);
        String[] keywords = {"test", "히히", "1"};      
        List<Post> list = //postDao.searchByModifiedTime(from, LocalDateTime.now());
        
        //postDao.searchByKeywordAndAuthor("히히", "admin");
          
        postDao.searchBykeywords(keywords);
                
        list.forEach((x) -> log.info((x).toString()));
    }
    
    @Test
    public void testPaging() {
        String[] keywords = {"test", "1"};
        
        Pageable pageable = PageRequest.of(1, 5, Sort.by("id").descending());
        Page<Post> list = postDao.searchByKeywords(keywords, pageable);
       
        list.forEach((x) -> log.info((x).toString()));
    }
    
}
