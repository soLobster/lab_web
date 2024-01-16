package com.itwill.springboot4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.domain.PostRepository;
import com.itwill.springboot4.dto.PostDto;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postDao;
    
    // 전체 포스트 목록을 가져옴.
//    public List<Post> getPostList(){
//        log.info("====================");
//        log.info("getPostList()");
//        log.info("====================");
//        
//        return postDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
//    }
    
    public Page<Post> getPostList(int page){
        log.info("getPostList(page = {})", page);
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());
        Page<Post> data = postDao.findAll(pageable);
        
        return data;
    }
    
    // 포스트 상세 페이지를 띄우기 위한 하나의 POST를 가져옴
    public Post getPostDetails(long id) {
        log.info("===================");
        log.info("getPostDetails()");
        log.info("===================");
        
        return postDao.findById(id).orElseThrow();
    }
    
    public void deletePost(long id) {
        log.info("===================");
        log.info("deletePost(id = {})", id);
        log.info("===================");
        
        postDao.deleteById(id);
    }
    
    public void updatePost(long id, PostDto dto) {
        log.info("===================");
        log.info("updatePost() id = {}" , id);
        log.info("updatePost() dto = {}" , dto);        
        log.info("===================");
        
        Post entity = postDao.findById(id).orElseThrow();
        log.info(">> FIND ENTITY ID() = {}", id);
        
        entity.update(dto.getTitle(), dto.getContent());
        log.info(">> UPDATE ENTITY = {}", entity);
        
        postDao.save(entity);
        log.info(">> SAVE ENTITY = {}", entity);
    }
    
    public void createPost(PostDto dto) {
        log.info("===================");
        log.info("createPost() dto = {}", dto);
        log.info("===================");
        
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .build();
        log.info("bulid post = {}", post);
        
        postDao.save(post);
        
        log.info("save post = {}", post);
    }
}
