package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.service.UserService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 디스패처 서블릿에서 호출하는 컨트롤러 메서드들을 가지고 있는 클래스.

@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    
    private final UserService userService;

    @GetMapping("/signup")
    public void signUp() {
        log.debug("GET -> signUp()");
    }
    
}
