package com.itwill.springboot4.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.itwill.springboot4.domain.Member;
import com.itwill.springboot4.domain.MemberRole;

// 1) UserDetails 인터페이스를 직접 구현하거나 implements
// 2) UserDetails 인터페이스를 구현하는 User 클래스를 상속 extends -> org.springframework.security.core.userdetails.User;

public class MemberSecurityDto extends User{

    private String email;
    
    private MemberSecurityDto(String username, String password, String email,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        // -> 상위 클래스 User는 기본 생성자를 갖고 있지 않기 때문에, 명시적으로 생성자를 호출해야함.
        
        this.email = email;
        // -> Super 보다 아래 있어야 한다.
    } 

    public static MemberSecurityDto fromEntity(Member entity) {
//        List<SimpleGrantedAuthority> authorities = 
//                entity.getRoles().stream().map((x) -> new SimpleGrantedAuthority(x.getAuthority()))
//                .toList();
        // GrantedAuthority를 구현한 SimpleGrantedAuthority
        
        // 위 아래 같은 코드
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(MemberRole role : entity.getRoles()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());
            authorities.add(authority);
        }

        return new MemberSecurityDto(entity.getUsername(), entity.getPassword(), entity.getEmail(), authorities);
    }
    
}
