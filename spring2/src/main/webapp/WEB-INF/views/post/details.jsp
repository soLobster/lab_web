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
                        <label for="id" class="form-label">번호</label> 
                        <input class="form-control" id="id" type="text"
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
                        <label for="content" class="form-label">이미지</label>
                        <img class="form-control" id="upload_image" src="../tmp/${post.saved_file}" style="width:30%; height:30%;" />
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
                
                <c:if test="${post.author eq signedInUser}">
                    <div class="card-footer">    
                        <!-- 작성자 아이디와 로그인 아이디가 일치할 경우에만 '수정하기' 버튼을 보여준다. -->
                            <c:url var="postModifyPage" value="/post/modify">
                                <c:param name="id" value="${post.id}" />
                            </c:url>
                            <a href="${postModifyPage}" class="btn btn-warning">수정하기</a>
                    </div>
                </c:if>
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
                                <textarea class="form-control" id="commentText" placeholder="댓글을 입력하세요"></textarea>
                                <!-- 댓글 작성자 아이디 - 로그인 사용자 아이디로 변경 value => el tags를 이용한다 -->
                                <input class="d-none" id="writer"
                                    value="${signedInUser}" />
                            </div>
                            <div class="col-2">
                                <button class="btn btn-outline-success"
                                    id="btnAddComment">등록</button>
                            </div>
                        </div>

                        <!-- 포스트에 달려 있는 댓글 목록을 보여줄 영역 -->
                        <div class="my-2" id="comments">TODO: 댓글 목록....</div>
                    </div>
                </div>
            </div>
            
            <!-- 댓글 업데이트 모달(다이얼로그) -->
            <div id="commentModal" class="modal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">댓글 수정</h5>
                            <button type="button" class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <input class="d-none" id = "modalCommentId"/>
                            <textarea class="form-control" id="modalCommentText"></textarea>    
                            <!-- 수정할 부분 -->
                        </div>
                        <div class="modal-footer">
                            <button type="button"
                                class="btn btn-secondary"
                                data-bs-dismiss="modal">취소</button>
                            <button id="btnUpdateComment" type="button"
                                class="btn btn-primary">댓글 변경</button>
                        </div>
                    </div>
                </div>
            </div> <!-- end of modal -->
            
            
        </main>


    </div>
    
    <!-- Bootstrap 자바 스크립트 모듈 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous">
    </script>

    <!-- Axios 자바 스크립트 모듈 -->
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <!-- 밑의 js/comments.js 에서 signedInUser 변수를 사용하기 위해서 여기서 상수를 선언한다 (JS) 왜냐면 JSP에서만 JAVA를 쓸 수 있지 JS에선 JAVA를 쓰지 못하기 때문  -->
    <script>
        const signedInUser = '${signedInUser}';
    </script>

    <!-- 부트스트랩 모듈을 사용하는 자바스크립트 파일은 모듈을 포함시킨 다음에 작성 -->
    <script src="../js/comment.js"></script>


</body>
</html>