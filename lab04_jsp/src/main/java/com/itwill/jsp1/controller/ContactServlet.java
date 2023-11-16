package com.itwill.jsp1.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itwill.jsp1.model.Contact;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet(name = "contactServlet", urlPatterns = { "/mvc" })
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("contactServlet::doGet() Call");

        // 컨트롤러에서 View를 호출(뷰로 요청을 전달)
        request.getRequestDispatcher("/WEB-INF/contact_form.jsp").forward(request, response); 
        // view를 부르는 방법.
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("contactServlet::doPost() Call");
        
        // 클라이언트가 보낸 요청 파라미터들을 읽음.
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        // Contact 타입 객체를 생성할 수 있음.
        Contact contact = new Contact(id, name, phone, email);
        System.out.println(contact);
        
        // 연락처 DB에 저장.
        
        // 인덱스 페이지로 redirect
        response.sendRedirect("/jsp1");
    }

}
