package com.itwill.springboot4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSearchRequestDto {

    private String category; // 검색 카테고리
    private String keyword; // 검색어
    private int page; // 검색 결과의 페이지 정보
}
