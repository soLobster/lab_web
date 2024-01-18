package com.itwill.springboot4.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.domain.CommentRepository;
import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.domain.PostRepository;
import com.itwill.springboot4.dto.CommentRegisterRequestDto;
import com.itwill.springboot4.dto.CommentUpdateDto;

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
    
    // 댓글 페이징
    public Page<Comment> getAllCommentsToPage(Long postId, int page){
        log.info("================");
        log.info("COMMENT SERVICE - GET ALL COMMENTS TO PAGE POST_ID = {}, PAGE = {}");
        log.info("================");
        
        // 댓글이 달려 있는 포스트 엔터티를 찾음
        Post post = postDao.findById(postId).orElseThrow();
        
        // 페이징과 정렬이 적용된 댓글 목록.
        Pageable pageable = PageRequest.of(page, 5, Sort.by("modifiedTime").descending()); // Entity의 필드 이름을 써야 한다.
        
        
        Page<Comment> allComments = commentDao.findByPost(post, pageable);
        
        log.info("allComments number = {} , TotalPages = {}", 
                allComments.getNumber(), allComments.getTotalPages());
        
        return allComments;
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
    
    @Transactional
    public Comment updateComment(Long id , CommentUpdateDto dto) {
        log.info("==============");
        log.info("COMMENT SERVICE - UPDATE COMMENT");
        log.info("COMMENT ID = {} , UPDATE DTO = {}", id, dto.getCtext());        
        log.info("==============");
        
        Comment comment =  commentDao.findById(id).orElseThrow();

        comment.updateComment(dto.getCtext());
        
        return comment;
    }
}
