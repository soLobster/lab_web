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
            <h1>포스트 목록 페이지</h1>
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
                    </ul>
                </div>
            </div>
        </nav>
        
        <main class="my-2">
            <div class="card p-2">
                <div class="card-header my-2">
                    <c:url var = "searchPage" value ="/post/search"/>
                    <form action = "${searchPage}" class="d-flex" role="search">
                        <select class="form-select " aria-label="Small select example" name="category">
                            <option selected>검색 주제 선택</option>
                            <option value="t">제목</option>
                            <option value="c">내용</option>
                            <option value="tc">제목+내용</option>
                            <option value="a">작성자</option>
                        </select>
                        <input class="form-control me-2" name="keyword" type="text" placeholder="Search" aria-label="Search" required autofocus/>
                        <input class="btn btn-primary" value="Search" type="submit"/>
                    </form>
                </div>
                <table class="table table-striped card-body">
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
                                    <c:param name="id" value="${p.id}" />
                                </c:url>
                                <a href="${postDetails}">${p.title}</a>
                            </td>
                            <td>${p.author}</td>
                            <td>${p.modifiedTime}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </main>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" 
            crossorigin="anonymous"></script>
    </body>
</html>