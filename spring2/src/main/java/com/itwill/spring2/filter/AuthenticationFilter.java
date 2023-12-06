package com.itwill.spring2.filter;

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
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@Slf4j
public class AuthenticationFilter extends HttpFilter implements Filter {

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
        // place your code here
        HttpSession session = ((HttpServletRequest) request).getSession();
        
        
        log.debug("URL >> {}", ((HttpServletRequest) request).getRequestURL());
        log.debug("query String >> {}", ((HttpServletRequest) request).getQueryString());
        
        String reqUrl = ((HttpServletRequest) request).getRequestURL().toString();
        String qs = ((HttpServletRequest) request).getQueryString();
        
        String target = "";
        
        if(qs == null) {
            target = URLEncoder.encode(reqUrl, "UTF-8");
        } else {
            target = URLEncoder.encode(reqUrl + "?" + qs, "UTF-8");
        }
        
        Object signedInUser = session.getAttribute("user");
        
        if(signedInUser == null) {
            log.debug("Log-Out ---> Log-In Page Redirect");
            String url = ((HttpServletRequest) request).getContextPath() + "/user/signin?target=" + target; 
            ((HttpServletResponse) response).sendRedirect(url);
        } else {
            log.debug("Log-In > {}", signedInUser);
            // pass the request along the filter chain
            chain.doFilter(request, response);
        }
        
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
