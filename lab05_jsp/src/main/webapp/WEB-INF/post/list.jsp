<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 2</title>

<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

</head>

<body>
    <header class="my-2 p-4 bg-dark text-white text-center">
        <h1>포스트 목록 페이지</h1>
    </header>

    <nav class="my-2 navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <ul class="navbar-nav">
            <li  class="nav-item">
                <c:url var="mainPage" value="/"/> <%-- context root --%>
                <a class="nav-link active" href="${mainPage}">메인 페이지</a>
            </li>
            <li class="nav-item">
                <c:url var="postCreate" value="/post/create"/>
                <a class="nav-link active" href="${postCreate}">새 포스트 작성</a>
            </li>
        </ul>
    </div>
    </nav>

    <main class="my-2">
        <div class="card p-2">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>수정시간</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${posts}">
                    <tr>
                        <td>${p.id}</td>
                        <td>
                            <c:url var="postDetails" value="/post/details">
                                    <c:param name="id" value="${p.id}"/>
                            </c:url>
                            <a href ="${postDetails}">${p.title}</a>
                        </td>
                        <td>${p.author}</td>
                        <td>${p.modifiedTime}</td>
                    </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </div>
    </main>
    
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>