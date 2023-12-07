package com.itwill.spring2.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.spring2.domain.User;
import com.itwill.spring2.dto.user.UserRegisterDto;
import com.itwill.spring2.dto.user.UserSignInDto;
import com.itwill.spring2.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    
    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserRegisterDto dto) {
        log.debug("POST signUp DTO = {}", dto);
        
        int result = userService.create(dto);
        
        log.debug("POST signUp result = {}",result);
        
        return "redirect:/user/signin";
    }
    
    @GetMapping("/signin")
    public void signin() {
        log.debug("GET - signin()");
    }
    
    @PostMapping("/signin")
    public String signin(@ModelAttribute UserSignInDto dto, HttpSession session, 
            @RequestParam(name = "target" , defaultValue = "") String target) throws UnsupportedEncodingException {
        log.debug("POST - signIn (dto = {}, session = {} , target = {})", dto, session, target);

        User user = userService.signIn(dto);

        //String target = request.getParameter("target");

        if (user != null) { // 아이디와 비밀번호 모두 일치하는 사용자가 있는 경우
            
            // 세션에 로그인 사용자 정보를 저장
            session.setAttribute("signedInUser", user.getUserid());
            
            log.debug("signin session = {}",session);
            
            // 타겟 페이지로 이동
            return (target.equals("")) ? "redirect:/" : "redirect:" + target;

        } else { // 아이디와 비밀번호 모두 일치하는 사용자가 없는 경우
            return "redirect:/user/signin?result=fail&target=" 
                + URLEncoder.encode(target, "UTF-8");
        }
    }
    
    @GetMapping("/signout")
    public String signout(HttpSession session) {
        // 세션에 저장된 "signedInUser" 정보를 삭제
        log.debug("signout session = {}",session);
        
        log.debug("signOut");
        session.removeAttribute("signedInUser");
        
        // 세션을 만료 시킴
        session.invalidate();
        
        return "redirect:/user/signin";
    }
    
    @GetMapping("/checkid")
    @ResponseBody // Ajax 서비스를 구현하는 ResponseBody
    public ResponseEntity<String> CheckId(@RequestParam(name = "userid") String userid) {
        log.debug("User checkId userid= {}",userid);
        
        boolean checkUserId = userService.checkUserId(userid);
        
        if(checkUserId) {
            return ResponseEntity.ok("Y");
        } else {
            return ResponseEntity.ok("N");
        }
    }
       
}
