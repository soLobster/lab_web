package com.itwill.springboot4.web;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.springboot4.dto.PostDto;
import com.itwill.springboot4.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostRestController {
    
    private final PostService postService;
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostDto dto){
        log.info("createPost() = {}", dto);
        
        ResponseEntity<?> entity = null;
        HttpHeaders headers = new HttpHeaders();
        
        try {
            postService.createPost(dto);
            headers.setLocation(URI.create("/post/list"));
            entity = new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return entity;
    }   
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id){
        log.info("deletePost() = {}", id);
        
        ResponseEntity<?> entity = null;
        HttpHeaders headers = new HttpHeaders();
        
        try {
            postService.deletePost(id);
            headers.setLocation(URI.create("/post/list"));
            entity = new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return entity;
         
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody PostDto dto) {
        log.info("updatePost() = {} ", id);
        log.info("POSTUPDATEDTO = {}", dto);
        
        ResponseEntity<?> entity = null;
        HttpHeaders headers = new HttpHeaders();
        try {
            postService.updatePost(id, dto);
            headers.setLocation(URI.create("/post/details/"+id));
            entity = new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (Exception e) {
            entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return entity;
    } 
}
