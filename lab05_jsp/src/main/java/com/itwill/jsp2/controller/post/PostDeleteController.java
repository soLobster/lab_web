package com.itwill.jsp2.controller.post;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.service.PostService;

/**
 * Servlet implementation class PostDeleteController
 */
@WebServlet(name = "postDeleteController", urlPatterns = { "/post/delete" })
public class PostDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(PostDeleteController.class);

    private final PostService postService = PostService.getInstance();
    
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("doGet()");
        
        // 요청 파라미터 중에서 ID를 찾는다.
        Long id = Long.valueOf(request.getParameter("id"));
        log.debug("--- id={}",id);
        
        // 서비스(비즈니스) 계층의 메서드를 호출해서 포스트를 삭제한다.
        postService.delete(id);
        
        // 포스트 목록 페이지로 리다이렉트.
        String url = request.getContextPath()+ "/post/list";
        response.sendRedirect(url); // jsp 파일이 되면 안된다.
        
    }
}
