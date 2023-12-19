<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring2</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
    crossorigin="anonymous">
</head>
<body>

<div class="container-fluid">
        <c:set var="title" value="파일 업로드" />
        <!-- header -->
        <%@ include file="../fragments/title.jspf"%>
        <!-- navigation -->
        <%@ include file="../fragments/navigation.jspf"%>

        <main class="my-2">
            <div class="my-2 p-2 card">
                <c:url var="postUploadPage"  value ="/post/upload"/>
                <form class="card-body" action="${postUploadPage}" method="post" enctype = "multipart/form-data">
                    
                    <div class="my-2">
                        <input class="form-control my-2" type="text" name="filename" />
                        <input class="form-control my-2" type="file" name="file"/>
                    </div>
                    <div class="d-none">
                        <!-- 작성자 아이디는 로그인한 사용자 아이디로 설정. -->
                        <input class="form-control"  type="text" name="author"  value="${signedInUser}"/>
                    </div>
                    <div class="my-2">
                        <input class="form-control btn btn-success"  type="submit" value="업로드"/>
                    </div>
                </form>
            </div>
        </main>


    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>