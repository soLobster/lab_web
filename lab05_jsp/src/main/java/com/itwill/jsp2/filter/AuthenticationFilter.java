package com.itwill.jsp2.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
public class AuthenticationFilter extends HttpFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);

    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        // log.debug("doFilter 호출 전 ----->");

        // 인증이 필요한 요청 주소들(예: 새 포스트, 상세보기)에 대해서
        // 로그인이 안 되어 있으면, 서블릿으로 요청을 보내지 말고 로그인 페이지로 이동.(redirect).
        // 로그인 되어 있으면, (서블릿으로 요청을 전달해서) 계속 요청 처리

        HttpSession session = ((HttpServletRequest) request).getSession(); // 부모 ServletRequest -> 자식 HttpServletRequest
                                                                           // 형변환
        Object signedInUser = session.getAttribute("signedInUser");
        if (signedInUser == null) { // 로그인 안 된 상태
            log.debug("Log-Out ---> Log-In Page Redirect");
            String url = ((HttpServletRequest) request).getContextPath() + "/user/signin"; // 로그인 페이지
            ((HttpServletResponse) response).sendRedirect(url); // 부모 ServletResponse -> 자식 HttpServletResponse 형변환
        } else { // 로그인 된 상태
            log.debug("Log-In > {}", signedInUser);
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }

        // log.debug("-----> doFilter 호출 후 ");
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
