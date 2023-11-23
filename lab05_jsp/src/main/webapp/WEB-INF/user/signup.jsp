<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>JSP 2</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		
	</head>

	<body>
    <header class = "my-2 p-4 bg-primary text-white text-center">
		<h1>회원 가입</h1>
	</header>	
        <nav></nav>
        
        <main>
            <div class ="m-4 card">
                <div class = "my-2 p-4 card-body">
                <c:if test="${not empty param.result && param.result eq 'fail'}">
                    <div class ="fs-3 text-danger">회원 가입 양식을 다시 작성하세요....</div>
                </c:if>
                    <form method = "post"> <%-- action 속성의 기본값은 현재 URL --%>
                        <div class = "my-2"><input class="form-control" type ="text" name="userid" placeholder="아이디를 입력하세요" required autofocus /></div>
                        <div class = "my-2"><input class="form-control" type ="text" name="password" placeholder="비밀번호를 입력하세요" required /></div>
                        <div class = "my-2"><input class="form-control" type ="text" name="email" placeholder="이메일을 입력하세요" required /></div>
                        <div class = "my-2"><input class="form-control btn btn-primary" type ="submit" value="가입하기"/></div>
                    </form>
                </div>
            </div>
        </main>
        
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
	</body>
</html>