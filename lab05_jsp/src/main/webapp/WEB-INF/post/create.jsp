<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>JSP 2</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" 
            rel="stylesheet" 
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" 
            crossorigin="anonymous">
    </head>
    <body>
        <header class="my-2 p-4 bg-dark text-white text-center">
            <h1>새 포스트 작성 페이지</h1>
        </header>
        
        <nav class="my-2 navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button"
                    data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <c:url var="mainPage" value="/" />
                            <a class="nav-link" href="${mainPage}">홈</a>
                        </li>
                        <li class="nav-item">
                            <c:url var="postList" value="/post/list" />
                            <a class="nav-link" href="${postList}">포스트 목록</a>
                        </li>
                        <li class="nav-item">
                            <c:url var="postCreate" value="/post/create" />
                            <a class="nav-link" href="${postCreate}">새 포스트</a>
                        </li>
                        <%-- 세션에 signedInUser 속성이 있으면(로그인되어 있으면) --%>
                        <c:if test="${not empty signedInUser}">
                            <li class="nav-item">
                                <c:url var="signOutPage" value="/user/signout"/>
                                <a class="nav-link" href="${signOutPage}"><span>${signedInUser}</span> 로그아웃</a>
                            </li>
                        </c:if>
                        <%-- 세션에 signedInUser 속성이 없으면(로그인 되어 있지 않으면) --%>
                        <c:if test="${empty signedInUser}">
                            <li class="nav-item">
                            <c:url var="signInPage" value="/user/signin"/>
                            <a class="nav-link" href="${signInPage}">로그인</a>
                        </li>
                        <li class="nav-item">
                            <c:url var="signUpPage" value="/user/signup"/>
                            <a class="nav-link" href="${signUpPage}">회원가입</a>
                        </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>
        
        <main class="my-2">
            <div class="card p-2">
                <c:url var="postCreate" value="/post/create" />
                <form action="${postCreate}" method="post">
                    <div class="my-2">
                        <input class="form-control" 
                            type="text" name="title" placeholder="제목" autofocus required />
                    </div>
                    <div class="my-2">
                        <textarea class="form-control"
                            name="content" rows="5" cols="80" placeholder="내용" required></textarea>
                    </div>
                    <%--  d(isplay)-none: 요소는 생성되지만 화면에서는 보이지 않음. 
                         작성자 input의 value를 로그인한 사용자 아이디로 채움.
                    --%>
                    <div class="my-2 d-none">
                        <input class="form-control"
                            type="text" name="author" value = "${signedInUser}" readonly/> 
                            <%-- UserSignInController -> 
                                 session.setAttribute("signedInUser", signedInUser.getUserid()); 
                            --%>
                    </div>
                    <div class="my-2">
                        <input class="form-control btn btn-success" 
                            type="submit" value="작성완료" />
                    </div>
                </form>
            </div>
        </main>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
            crossorigin="anonymous"></script>
    </body>
</html>