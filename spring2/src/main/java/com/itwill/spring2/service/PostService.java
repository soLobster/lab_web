package com.itwill.spring2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.post.PostCreateDto;
import com.itwill.spring2.dto.post.PostListItemDto;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service // 스프링 컨테이너에서 관리하는 서비스 컴포넌트.
public class PostService {
    // PostDao를 주입 받음.
//    @Autowired
//    private PostDao postDao;

    private final PostDao postDao;
    
//    public PostService(PostDao postDao) {
//        this.postDao = postDao;
//    }

    public List<PostListItemDto> read() {
        log.debug("read()");
        
        // postDao의 메서드를 호출해서 포스트 목록을 리턴받고,
        List<Post> list = postDao.selectOrderByIdDesc();

        log.debug("List = {}", list);
        log.debug("Number Of List = {}", list.size());

        // 컨트롤러에게 리턴해준다.

        return list.stream()
                .map(PostListItemDto::fromEntity) // map((x) -> PostListItemDto.fromEntity(x))
                .toList();
    }// end read() method
    
    public int create(PostCreateDto dto) {
        log.debug("PostService - create(dto = {})", dto);
        
        // Repository 계층의 메서드를 호출해서 테이블에 데이터 insert.
        int result = postDao.insert(dto.toEntity());
        
        log.debug("PostService - create(result = {})", result);
        
        return result;
    }// end create(dto)
    
    public Post details(long id) {
        log.debug("PostService_details(id = {})", id);
        
        Post post = postDao.selectById(id);
        
        log.debug("PostService_details post = {}", post);
        
        return post;
    }// end details
}
