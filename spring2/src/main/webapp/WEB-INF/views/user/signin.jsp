<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>SPRING 2</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
		
	</head>

	<body>
		<div class="container-fluid">
        <c:set var="title" value="로그인 페이지" />
        <%@ include file="../fragments/title.jspf" %>
        
        <main class="my-2">
            <div class="my-2 card card-body">
            <c:url var="signInPage" value="/user/signin" />
                <form action="${signInPage}" method="post">
                   <div class="my-2">
                        <input type="text" class="form-control"
                            id="userid" name="userid" placeholder="아이디를 입력하세요." required autofocus />
                    </div>
                    <div class="my-2">
                        <input type="password" class="form-control"
                            id="password" name="password" placeholder="비밀번호를 입력하세요." required />
                    </div>
                    <div>
                        <button type="submit" id="btnSignIn" 
                            class="form-control btn btn-success">로그인</button>
                    </div>
                </form>
            </div>
        </main>
        
        
        </div>
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        
        
	</body>
</html>