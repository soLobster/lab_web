package com.itwill.springboot4.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itwill.springboot4.domain.Member;
import com.itwill.springboot4.domain.MemberRepository;
import com.itwill.springboot4.dto.MemberSecurityDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService{
    
    @Autowired
    private final MemberRepository memberDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // MemberRepository의 메서드를 호출해서 username이 일치하는 사용자 정보가 있는 지를 
        // 리턴. 만약 사용자 정보가 없으면 Exception을 던진다.
        log.info("loadUserByUsername - USERNAME = {}", username);
        
        Optional<Member> option = memberDao.findByUsername(username);
        if(option.isPresent()) {
            return MemberSecurityDto.fromEntity(option.get());
        } else {
            throw new UsernameNotFoundException("There is no "+username);
        }        
    }// end loadUserByUsername method
}
