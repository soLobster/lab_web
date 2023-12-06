package com.itwill.spring2.dto.user;

import com.itwill.spring2.domain.User;

import lombok.Data;

// 로그인을 하는 정보를 저장하는 DTO

@Data
public class UserSignInDto {

    private String userid;
    private String password;
    
    public User toEntity() {
        return User.builder().userid(userid).password(password).build();
    }
    
}
