package com.itwill.springboot4.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long>{

    //List<Comment> findByPostIdOrderByIdDesc(Long postId);
    List<Comment> findByPostId(Long postId, Sort sort);
    
}
