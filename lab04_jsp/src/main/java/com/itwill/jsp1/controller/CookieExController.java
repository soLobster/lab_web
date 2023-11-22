package com.itwill.jsp1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CookieExController
 */
@WebServlet(name = "cookieExController", urlPatterns = {"/excookie"})
public class CookieExController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("cookieExController::doGet() 호출");
		
		// 쿠키 객체 생성
		Cookie cookie1 = new Cookie("my-cookie", "안녕하세요");
		
		// 생성된 쿠키 객체를 응답(response)에 포함
		response.addCookie(cookie1);
		
		// 클라이언트(브라우저)에서 보낸 쿠키(들)을 확인 하는 방법.
		Cookie[] cookies = request.getCookies();
		
		for(Cookie c : cookies) {
		    System.out.println(c.getName() + " = " + c.getValue());
		}
		
		request.getRequestDispatcher("/WEB-INF/cookie.jsp").forward(request, response);
	}

}
