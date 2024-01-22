package com.itwill.springboot4.domain;

//public static final을 선언하고 있는 특별한 클래스 = ENUM
public enum MemberRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    
    private String authority;
    
    MemberRole(String authority) {
        this.authority = authority;
    }
    
    public String getAuthority() {
        return this.authority;
    }
}
