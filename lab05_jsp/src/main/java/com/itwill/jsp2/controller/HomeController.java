package com.itwill.jsp2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class HomeController
 */

// "http://localhost:8081/jsp2" context root를 처리하는 서블릿
@WebServlet(name = "homeController", urlPatterns = { "" })
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //Logger 타입 org.slf4j.Logger를 임포트 해야한다.
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("homeController::doGet()");
        log.debug("doGet");
        
        // 요청(request)를 뷰(view)로 전달.
        request.getRequestDispatcher("/WEB-INF/home.jsp")
        .forward(request, response);

        System.out.println("homeController::doGet() Call");

        request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

}
