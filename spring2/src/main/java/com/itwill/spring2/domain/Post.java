package com.itwill.spring2.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

// DB Table => Posts 테이블 - 필드 이름은 컬럼 이름과 같게!!

@Data
@Builder
public class Post {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime created_time;
    private LocalDateTime modified_time;
    // 파일 첨부를 위한 도메인 추가
    private String original_file;
    private String saved_file;
}
