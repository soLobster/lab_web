package com.itwill.spring2.dto.comment;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

    private Long id;
    private String commentText;

    public Comment toEntity() {
        return Comment.builder().id(id).comment_text(commentText).build();
    }

}
