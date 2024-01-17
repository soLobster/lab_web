package com.itwill.springboot4.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRegisterRequestDto {

    private Long postId;
    private String ctext;
    private String writer;
    
}
