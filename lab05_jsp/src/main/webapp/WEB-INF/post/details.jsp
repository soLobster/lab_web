<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
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
        <h1>포스트 상세보기 페이지</h1>
    </header>

    <nav class="my-2"></nav>

    <main class="my-2">
        <div class="card">
            <form class="card-body">
                <div class="my-2">
                    <label for="id" class="form-label">번호</label>
                    <input
                        class="form-control" id="id" type="number"
                        value="${post.id}" readonly />
                </div>
                <div class="my-2">
                    <label for="title" class="form-label">제목</label>
                    <input
                        class="form-control" id="title" type="text"
                        value="${post.title}" readonly />
                </div>
                <div class="my-2">
                    <label for="content" class="form-label">내용</label>
                    <textarea class="form-control" id="content" rows="5"
                        cols="80" readonly>${post.content}</textarea>
                </div>
                <div class="my-2">
                    <label for="title" class="form-label">작성자</label>
                    <input class="form-control" id="author"
                        value="${post.author}" readonly />
                </div>
                <div class="my-2">
                <label for="createdTime" class="form-label">작성 시간</label>
                    <input class="form-control" id="createdTime" type="text" value="${post.createdTime}"
                        readonly />
                </div>
                <div class="my-2">
                <label for="modifiedTime" class="form-label">수정 시간</label>
                    <input class="form-control" id="modifiedTime" type="text" value="${post.modifiedTime}"
                        readonly />
                </div>
            </form>
            <div class ="card-footer">
                <c:url var="postModify" value="/post/modify">
                    <c:param name="id" value ="${post.id}"></c:param>
                </c:url>
                <a class="btn btn-primary form-control" href="${postModify}">수정</a>
            </div>
        </div>
    </main>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>