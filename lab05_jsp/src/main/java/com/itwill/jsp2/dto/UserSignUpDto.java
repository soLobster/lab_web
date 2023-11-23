package com.itwill.jsp2.dto;

public class UserSignUpDto {
    private String userid;
    private String password;
    private String email;

    public UserSignUpDto() {
        // TODO Auto-generated constructor stub
    }

    public UserSignUpDto(String userid, String password, String email) {
        this.userid = userid;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserSignUpDto [userid=" + userid + ", password=" + password + ", email=" + email + "]";
    }

    
    // --- Builder Pattern
    public static UserSignDtoBuilder builder() {
        return new UserSignDtoBuilder();
    }

    public static class UserSignDtoBuilder {
        private String userid;
        private String password;
        private String email;

        private UserSignDtoBuilder() {
        }

        public UserSignDtoBuilder userid(String userid) {
            this.userid = userid;
            return this;
        }

        public UserSignDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserSignDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserSignUpDto build() {
            return new UserSignUpDto(userid, password, email);
        }
    }
}
