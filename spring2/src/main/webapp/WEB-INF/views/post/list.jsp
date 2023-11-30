<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING 2</title>

<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">

</head>

<body>
    <div class="container-fluid">
    <!-- header -->
    <c:set var="title" value="포스트 목록"/>
    <%@ include file="../fragments/title.jspf" %>
    
    <!-- navigation -->
    <%@ include file="../fragments/navigation.jspf" %>
    
        <main class="my-2">
        <!-- post list table -->
            <div class="card">
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
                <table class="table table-striped table-hover card-body">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>수정시간</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${postList}">
                            <tr>
                                <td>${p.id}</td>
                                <td>
                                    <c:url var="postDetailsPage" value="/post/details" >
                                        <c:param name="id" value="${p.id}"/>
                                    </c:url>
                                    <a href="${postDetailsPage}">${p.title}</a>
                                </td>
                                <td>${p.author}</td>
                                <td>${p.modifiedTime}</td> <!-- DTO의 필드 이름으로 해야한다. -->
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
    </div>
</body>
</html>