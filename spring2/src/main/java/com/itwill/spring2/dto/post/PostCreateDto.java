package com.itwill.spring2.dto.post;

import org.springframework.web.multipart.MultipartFile;

import com.itwill.spring2.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateDto {

    private String title;
    private String content;
    private String author;
    private MultipartFile original_file;
    private String saved_file;
    // PostCreateDTO 객체를 Post 모델(엔티티) 객체로 변환해서 리턴하는 메서드.
    
    
    public Post toEntity() {
        return Post.builder().title(title).content(content).original_file(original_file.getOriginalFilename()).saved_file(saved_file).author(author).build();
    }
    
}
