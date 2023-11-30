package com.itwill.spring2.dto.post;

import com.itwill.spring2.domain.Post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUpdateDto {

    private Long id;
    private String title;
    private String content;
    
    public Post toEntity() {
        return Post.builder().id(id).title(title).content(content).build();
    }
}
