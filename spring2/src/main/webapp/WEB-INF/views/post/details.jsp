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
        <c:set var="title" value="포스트 상세보기" />
        <%@ include file="../fragments/title.jspf"%>

        <!-- navigation -->
        <%@ include file="../fragments/navigation.jspf"%>

        <main class="my-2">
            <div class="card">
                <form class="card-body">
                    <div class="my-2">
                        <label for="id" class="form-label">번호</label> <input
                            class="form-control" id="id" type="text"
                            value="${post.id}" readonly />
                    </div>
                    <div class="my-2">
                        <label for="title" class="form-label">제목</label>
                        <input class="form-control" id="title"
                            type="text" value="${post.title}" readonly />
                    </div>
                    <div class="my-2">
                        <label for="content" class="form-label">내용</label>
                        <textarea class="form-control" id="content"
                            rows="5" cols="80" readonly>${post.content}</textarea>
                    </div>
                    <div class="my-2">
                        <label for="title" class="form-label">작성자</label>
                        <input class="form-control" id="author"
                            value="${post.author}" readonly />
                    </div>
                    <div class="my-2">
                        <label for="createdTime" class="form-label">작성
                            시간</label> <input class="form-control"
                            id="createdTime" type="text"
                            value="${post.created_time}" readonly />
                    </div>
                    <div class="my-2">
                        <label for="modifiedTime" class="form-label">수정
                            시간</label> <input class="form-control"
                            id="modifiedTime" type="text"
                            value="${post.modified_time}" readonly />
                    </div>
                </form>
                <div class="card-footer">
                    <c:url var="postModifyPage" value="/post/modify">
                        <c:param name="id" value="${post.id}" />
                    </c:url>
                    <a href="${postModifyPage}" class="btn btn-warning">수정하기</a>
                </div>
            </div>


            <!-- 댓글 보기 div 태그  -->
            
            <div class="my-2 card">
                <div class="card-header d-inline-flex gap-1">
                    <!-- collapse(접기/펼치기) 기능 버튼 -->
                    <button class = "btn btn-secondary" id = "btnToggleComment">댓글 보기</button>
                </div>
                <!-- 댓글 토글 버튼에 의해서 접기/펼치기를 할 영역 -->
                <div class="card-body collapse" id="collapseComments">
                    <div class="card card-body">
                        <!-- 내 댓글 등록 -->
                        <div class="row my-2">
                            <div class="col-10">
                                <!-- 댓글 입력 창 -->
                                <textarea class="form-control"
                                    id="commentText"
                                    placeholder="댓글을 입력하세요"></textarea>
                                <!-- 댓글 작성자 아이디 - TODO: 로그인 사용자 아이디로 변경 -->
                                <input class="d-none" id="writer"
                                    value="admin" />
                            </div>
                            <div class="col-2">
                                <button class="btn btn-outline-success"
                                    id="btnAddComment">등록</button>
                            </div>
                        </div>

                        <!-- 포스트에 달려 있는 댓글 목록을 보여줄 영역 -->
                        <div class="my-2" id="comments">TODO: 댓글
                            목록....</div>

                    </div>
                </div>
            </div>
        </main>


    </div>
    
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous">
    </script>

    <!-- 부트스트랩 모듈을 사용하는 자바스크립트 파일은 모듈을 포함시킨 다음에 작성 -->
    <script src="../js/comment.js"></script>


</body>
</html>