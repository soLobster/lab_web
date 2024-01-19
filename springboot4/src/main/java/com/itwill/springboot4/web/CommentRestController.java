package com.itwill.springboot4.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.springboot4.domain.Comment;
import com.itwill.springboot4.dto.CommentRegisterRequestDto;
import com.itwill.springboot4.dto.CommentUpdateDto;
import com.itwill.springboot4.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentSvc;
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Comment> regComment(@RequestBody CommentRegisterRequestDto dto){
        log.info("================");
        log.info("createComment() DTO = {}", dto);
        log.info("================");
        
        Comment regComment = commentSvc.regComment(dto);
        log.info("id = {} , created = {}", regComment.getId(), regComment.getCreatedTime());
        
        return ResponseEntity.ok(regComment);
    }
    
    @GetMapping("/all/{postId}")
    public ResponseEntity<Page<Comment>> getAllComments(@PathVariable (name = "postId") Long postId, @RequestParam (name = "page") int page){
        log.info("================");
        log.info("getAllComments POSTID = {}, PAGE = {}", postId, page);
        log.info("================");
        
        Page<Comment> commentList = commentSvc.getAllCommentsToPage(postId, page);
        
        return ResponseEntity.ok(commentList);
    }
    
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteComment(@PathVariable (name = "id") Long id){
        log.info("==============");
        log.info("deleteComment COMMENT ID = {}", id);
        log.info("==============");
        
        ResponseEntity<?> result = null;
        
        try {
            commentSvc.deleteComment(id);
            result = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
       return result; 
    }
    
    @PreAuthorize("hasRole('USER')")
    @PutMapping("{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable (name = "id") Long id, @RequestBody CommentUpdateDto dto){
        log.info("==============");
        log.info("updateComment COMMENT ID = {}", id);
        log.info("==============");
        
        Comment updateComment = commentSvc.updateComment(id, dto);
        
        return ResponseEntity.ok(updateComment);
    }
}
