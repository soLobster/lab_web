package com.itwill.jsp2.dto;

import com.itwill.jsp2.domain.User;

public class UserSignInDto {

    private String userid;
    private String password;
    
    public UserSignInDto() {}

    public UserSignInDto(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignInDto [userid=" + userid + ", password=" + password + "]";
    }
    
    public User toUser() {
        
        return User.builder().userid(userid).password(password).build();
    }
    
    // Builder Pattern
    public static UserSignInDtoBuilder builder() {
        return new UserSignInDtoBuilder();
    }
    
    public static class UserSignInDtoBuilder {
        private String userid;
        private String password;
        
        private UserSignInDtoBuilder() {}
        
        public UserSignInDtoBuilder userid(String userid) {
            this.userid = userid;
            return this;
        }
        
        public UserSignInDtoBuilder password(String password) {
            this.password = password;
            return this;
        }
        
        public UserSignInDto build() {
            return new UserSignInDto(userid, password);
        }
    }
}
