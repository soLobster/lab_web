package com.itwill.jsp2.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 메시지 요청된 리소스 [/jsp2/user/signout]은(는) 가용하지 않습니다. -> 서블릿이 없음을 표시

/**
 * Servlet implementation class UserSignOutController
 */
@WebServlet(name = "userSignOutController", urlPatterns = { "/user/signout" })
public class UserSignOutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UserSignOutController.class);

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("doGet()::Calls");

        // 로그아웃:
        // 1) 세션에 저장된 로그인 관련 정보 삭제
        // 2) 세션 객체 무효화

        HttpSession session = request.getSession();
        // UserSignInController에서 setAttribute(name, value)를 호출할 때 사용했던 속성 name으로 제거.
        session.removeAttribute("signedInUser"); // 1) 세션에 저장된 로그인 관련 정보 삭제.
        
        session.invalidate(); // 2) 세션 객체 무효화
        
        // 로그아웃 이후에는 홈 페이지로 이동.
        response.sendRedirect(request.getContextPath());
    }

}
