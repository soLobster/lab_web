<%-- 페이지 지시문(page directive): JSP 페이지 설정, 컨텐트 타입, 인코딩,... --%>

<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%> 

<%-- trimDirectiveWhitespaces 개발자 도구에서 응답 내용의 html 공백 삭제 --%>    
    
<%-- JSP 주석 
        1. Servlet(Server + Applet)의 동작 방식:
            - Servlet: WAS에서 실행되는, 요청을 처리하고 응답을 보낼 수 있는 작은 자바 프로그램.
            - 서블릿 객체 생성과 관리, 서블릿 메서드 호출은 WAS가 담당한다.
            - 최초 요청 -> 서블릿(생성자 호출) 객체 생성 -> 메서드(doGet, doPost,...) 호출 -> 응답
            - 요청 -> 생성된 서블릿 객체에서 메서드 호출 -> 응답
            
         2. JSP(Java/Jakarta Server Page)
            - 서블릿은 순수 자바 클래스 코드이기 때문에 HTML을 작성하기 힘듦.
            - HTML 형식의 파일에서 자바 코들이 실행될 수 있도록 만든 Server-side 문법.
            - 동작 원리: jsp -> java 변환 -> class 컴파일 -> 객체 생성 -> 메서드 호출 -> 응답
                - 최초 요청: JSP를 Java로 변환한다. -> Java를 클래스로 컴파일 -> 객체 생성 -> 메서드 호출
                - 요청 -> 생성된 객체에서 바로 메서드를 호출한다. -> 응답
         
         3. JSP 구성요소 (문법)
            - 주석 (comment): JSP가 Java로 변환될 때 무시되는 코드.
            - 지시문 (directive): <%@ .... %>   
                ex) <%@ page .... %>, <%@ include .... %> , <%@ taglib ... %> 
            - 선언문 (declatation): <%! ... %> 
                JSP가 Java로 변환될 때, 클래스의 필드, 메서드를 선언하는 부분.
         
         4. 스크립트릿(Scriptlet): <% ... %> 
            - JSP가 Java로 변환될 때, _jspService(req, res) 메서드 안에 포함되는 자바 코드.
            - 지역 변수 선언 & 초기화, 메서드 호출, 조건문, 반복문,....
         
         5. 식, 표현식(expression): <%= ... %> 
            JSP가 Java로 변환될 때, out.write(arg) 메서드의 아규먼트로 전달되는 값.
            HTML 코드에 문자열이 삽입이 된다.
         
    --%>

<%!/* JSP declaration */
    // 선언문(declaration)

    private static final String NAME = "scott"; // static final 상수 필드를 선언.

    // 메서드 선언.
    private void myLog(String msg) {
        System.out.println(msg);
    }%>

<%
//JSP scriptlet
myLog("intro.jsp 실행");

LocalDateTime now = LocalDateTime.now(); // 지역 변수
String dateStr = String.format("%d-%d-%d", now.getYear(), now.getMonthValue(), now.getDayOfMonth());
String timeStr = String.format("%d:%d:%d", now.getHour(), now.getMinute(), now.getSecond());
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 1</title>
</head>

<body>
    <h1>JSP 소개</h1>
    <%
    System.out.println(LocalDateTime.now());
    %>
    <h2>날짜: <%= dateStr %></h2> <%-- 식(expression) --%>
    <h2>시간: <%= timeStr %></h2>
    <h3>이름: <%= NAME %></h3>
</body>
</html>