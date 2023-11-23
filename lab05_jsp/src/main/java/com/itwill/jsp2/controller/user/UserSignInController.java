package com.itwill.jsp2.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.User;
import com.itwill.jsp2.dto.UserSignInDto;
import com.itwill.jsp2.service.UserService;

/**
 * Servlet implementation class UserSignInController
 */
@WebServlet(name = "userSignInController" , urlPatterns = {"/user/signin"})
public class UserSignInController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UserSignInController.class);
    
    private final UserService userService = UserService.getInstance();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       log.debug("doGet::Calls");
       
       request.getRequestDispatcher("/WEB-INF/user/signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("doPost::Calls");
        
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        
        UserSignInDto dto = UserSignInDto.builder().userid(userid).password(password).build();
        
        log.debug("dto={}",dto);
        
        User result = userService.signIn(dto);
        
        log.debug("signIn result = {}",result);
        
        if(result != null) {
            response.sendRedirect(request.getContextPath()+"/post/list");
        } else {
            String url = request.getContextPath()+"/user/signin";
            response.sendRedirect(url);
        }
        
       //TODO 요청 파라미터 userid, password를 찾는다.
        // 서비스 메서드를 호출하면서 로그인 정보를 전달한다.
        // 성공이면 포스트 목록 페이지로 이동, 실패면 로그인 페이지로 이동.
    }

}
