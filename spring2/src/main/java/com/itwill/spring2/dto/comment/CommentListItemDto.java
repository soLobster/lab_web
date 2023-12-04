package com.itwill.spring2.dto.comment;

import java.sql.Timestamp;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentListItemDto {

    private Long id;
    private String commentText;
    private String writer;
    private Timestamp modifiedTime;

    // 엔터티 Comment 객체를 DTO 객체로 변환해서 리턴.
    public static CommentListItemDto fromEntity(Comment comment) {
        return CommentListItemDto.builder()
                .id(comment.getId())
                .commentText(comment.getComment_text())
                .writer(comment.getWriter())
                .modifiedTime(Timestamp.valueOf(comment.getModified_time()))
                .build();
    }
}
