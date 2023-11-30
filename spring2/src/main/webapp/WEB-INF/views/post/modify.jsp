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
        <c:set var="title" value="포스트 수정" />
        <!-- header -->
        <%@ include file="../fragments/title.jspf"%>
        <!-- navigation -->
        <%@ include file="../fragments/navigation.jspf"%>

        <main class="my-2">
            <div class="card">
                <form class="card-body">
                    <div class="d-none my-2">
                        <label for="id" class="form-label">번호</label> <input
                            class="form-control" id="id" type="text"
                            name="id" value="${post.id}" readonly />
                    </div>
                    <div class="my-2">
                        <label for="title" class="form-label">제목</label>
                        <input class="form-control" id="title"
                            name="title" type="text"
                            value="${post.title}" autofocus="autofocus" />
                    </div>
                    <div class="my-2">
                        <label for="content" class="form-label">내용</label>
                        <textarea class="form-control" id="content"
                            name="content" rows="5" cols="80">${post.content}</textarea>
                    </div>
                    <div class="my-2">
                        <label for="title" class="form-label">작성자</label>
                        <input class="form-control" id="author"
                            value="${post.author}" readonly />
                    </div>
                </form>
                <div class="card-footer">
                    <button class="btn btn-danger">삭제</button>
                    <button class="btn btn-success">수정</button>
                </div>
            </div>
        </main>
        
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    </div>
</body>
</html>