package com.itwill.spring2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

// 데이터베이스에 있는 USERS 테이블의 모델(엔터티)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    private Long id; // PK - 오라클 시퀀스
    private String userid; // 로그인 아이디 (unique, not null)
    private String password; // 비밀번호 (not null)
    private String email; // not null
    private Long points; 
    
}
