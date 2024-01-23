package com.itwill.springboot4.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot4.dto.MemberSignUpDto;
import com.itwill.springboot4.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
    
    @Autowired
    private MemberService memberSVC;

    @GetMapping("/login")
    public void login() {
        log.info("GET - LOGIN ()");
    }
    
    @GetMapping("/signup")
    public void signup() {
        log.info("GET - SIGN UP");
    }
    
    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberSignUpDto dto) {
        log.info("POST - SIGN UP = {}",dto);
        
        try {
            memberSVC.signUp(dto);
        } catch (Exception e) {
            log.info("ERROR...");
        }
        
        return "redirect:/";
    }
    
}
