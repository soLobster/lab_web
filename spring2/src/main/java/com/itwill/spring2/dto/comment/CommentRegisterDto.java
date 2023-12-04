package com.itwill.spring2.dto.comment;

import com.itwill.spring2.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data = @Getter + @Setter + @ToString + @EqualsAndHashCode + @RequiredArgsConstructor
@NoArgsConstructor // 아규먼트를 갖지 않는 생성자. 기본 생성자.
@AllArgsConstructor // 모든 필드를 초기화할 수 이쓴 아규먼트들을 갖는 생성자. 아규먼트 3개
@Builder // -> Builder 패턴의 build() 메서드에서 AllArgsConstructor를 사용하기 때문에
public class CommentRegisterDto {

    private Long postId;
    private String commentText;
    private String writer;
    
    
    // CommentRegisterDto 타입 객체를 모델(엔터티) Comment 객체로 변환해서 리턴.
    public Comment toEntity() {
        return Comment.builder().postid(postId).comment_text(commentText).writer(writer).build();
    }
    
}