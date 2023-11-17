package com.itwill.jsp2.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.Post;

public class PostDaoTest {

    private static final Logger log = LoggerFactory.getLogger(PostDaoTest.class);
    private PostDao dao = PostDao.getInstance();

    @Test
    public void testSelect() {
        Assertions.assertNotNull(dao); // PostDao 객체는 생성되어 있어야함.

        List<Post> list = dao.select();
        Assertions.assertNotEquals(0, list.size()); 
        // list size는 0이되면 안된다! 즉 1개 이상이 있어야한다.
        
        for(Post p : list) {
            log.debug(p.toString());
        }
    }

}
