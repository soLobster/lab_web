package com.itwill.springboot4.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.domain.CommentRepository;
import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.domain.PostRepository;
import com.itwill.springboot4.dto.CommentRegisterRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentDao;
    private final PostRepository postDao;
    
    public Comment regComment(CommentRegisterRequestDto dto) {
        log.info("====================");
        log.info("COMMENT SERVICE - REG-COMMENT = {}" , dto);
        log.info("====================");
        
        Post post = postDao.findById(dto.getPostId()).orElseThrow();
        
        Comment entity = Comment.builder()
                .post(post)
                .ctext(dto.getCtext())
                .writer(dto.getWriter())
                .build();
        
        Comment regComment = commentDao.save(entity);
        
        return regComment;
    }
    
    
    public List<Comment> getAllComments(Long postId){
        log.info("====================");
        log.info("COMMENT SERVICE - GET ALL COMMENTS post_ID = {}", postId);
        log.info("====================");
        
        Post post = postDao.findById(postId).orElseThrow();
        
        List<Comment> commentList = commentDao.findByPost(post, Sort.by("id").descending());
        
        return commentList;
    }
    
    public void deleteComment(Long id) {
        log.info("======================");
        log.info("COMMENT SERVICE - DELETE COMMENT - COMMENT ID = {}", id);
        log.info("======================");
        
        commentDao.deleteById(id);
    }
}
