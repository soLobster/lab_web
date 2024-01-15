package com.itwill.springboot4.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postDao;
    
    //@Test
    public void test() {
       Assertions.assertNotNull(postDao); 
        
       // DB 테이블에서 전체 검색 테스트.
       
       List<Post> posts = postDao.findAll();
       
       posts.forEach((x) -> log.info(x.toString()));
       
//       for(Post post : posts) {
//           log.info("POST = {}", post);
//       }
    }
    
    //@Test
    public void testSave() {
        // DB 테이블에 INSERT 테스트
        Post entity = Post.builder()
                .title("insertTest")
                .content("Do It Insert Test")
                .author("JPATEST")
                .build();
        log.info("Before Save = {}", entity);
        
        postDao.save(entity);
        // 엔터티의 id 필드가 null인 경우 insert 쿼리를 실행.
        // 엔터티의 id 필드 값으로 검색(select) 할 수 있는 레코드가 없는 경우, insert.
        
        log.info("After Save = {}" , entity);
        log.info("get ID = {}", entity.getId());
    }
    
    //@Test
    public void testUpdate() {
        // PK(id)로 포스트 엔터티를 검색.
        Post entity = postDao.findById(3L).orElseThrow();
        log.info("1 >> LOAD entity ID (1) ={}", entity);
        
        // 업데이트할 필드들을 수정.
        entity.update("UPDATE TEST", "DO IT UPDATE...!");
        log.info("2 >> UPDATE entity = {}", entity);
        
        // 엔터티를 저장한다. (@Transactional 애너테이션을 사용하는 경우에는 save를 호출할 필요가 없음.
        postDao.save(entity);
        log.info("3 >> SAVE entity = {}", entity);
        
    }
    
    @Test
    public void testDelete() {
        postDao.deleteById(2L);
        // id로 select 쿼리를 실행한 후, 엔터티가 존재할 때 delete 쿼리를 실행함.
    }
    
}// end of PostRepositoryTest
