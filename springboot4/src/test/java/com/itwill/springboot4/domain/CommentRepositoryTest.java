package com.itwill.springboot4.domain;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;


@Slf4j
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentDao;
    
    @Autowired
    private PostRepository postDao;
    
    @Test
    public void insertTest() {
        Post post = postDao.findById(316L).orElseThrow();
        Comment entity = Comment.builder()
                .post(post)
                .ctext("누구세욤")
                .writer("test")
                .build();
        log.info("before save = {} , {}", entity, entity.getCreatedTime());
        
        commentDao.save(entity);
        
        log.info("after save = {} , {}", entity, entity.getCreatedTime());
    }
    
    //@Test
    public void readByPostid() {
        //List<Comment> list = commentDao.findByPostIdOrderByIdDesc(316L);
        Post post = postDao.findById(316L).orElseThrow();
        List<Comment> list = commentDao.findByPost(post, Sort.by("id").descending());
        for(Comment comment : list) {
            log.info("comment = {}",comment);
        }
    }
    
}
