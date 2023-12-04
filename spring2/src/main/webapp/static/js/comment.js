/**
 * comment.js
 * 
 * 댓글 목록 접기/펼치기
 * 
 * 댓글 등록
 * 
 * 댓글 수정
 * 
 * 댓글 삭제
 */

 document.addEventListener('DOMContentLoaded', () => {
     // bootstrap 모듈의 Collapse 생성자를 호출해서 bootstrap Collapse 객체 생성.
     const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false});
     // JS 객체 생성 => {} toggle false => 접혀있는 상태.
     // console.log(bsCollapse);
     
     // btnToggleComment 요소를 찾음.
     const btnToggleComment = document.querySelector('button#btnToggleComment');
     // btnToggleComment에 클릭 이벤트 리스너를 등록.
     btnToggleComment.addEventListener('click', () => {
         bsCollapse.toggle(); // Collapse 객체의 toggle() 메서드 호출
         
         if(btnToggleComment.textContent === '댓글 보기'){
             btnToggleComment.textContent = '댓글 가리기';
             
             // 현재 포스트의 댓글 전체 목록 요청
             getAllComments();
             
         } else {
             btnToggleComment.textContent = '댓글 보기';
         }
     });
     
     // button#btnAddComment 요소를 찾음
     const btnAddComment = document.querySelector('button#btnAddComment');
     // btnAddComment에 click eventListener 등록 -> 댓글 등록.
     btnAddComment.addEventListener('click', registerComment); // 함수 이름만 등록 (콜백 함수)
     /*
     * 댓글 등록 버튼의 이벤트 리스너(콜백)
     */
    // 함수 위치 function이라고 선언한 함수들은 위에 있든 아래에 있든 JS가 찾을 수 있다.
    
     function registerComment(event){
         // 댓글을 등록할 포스트 번호: 
         const postId = document.querySelector('input#id').value;
         // 댓글 내용: 
         const commentText = document.querySelector('textarea#commentText').value;
         // 댓글 작성자:
         const writer = document.querySelector('input#writer').value;
         
         // 댓글 내용이 비어 있는 지 체크.
         if(commentText === ''){
             alert('댓글을 입력하세요.');
             return; //콜백 함수를 종료 시킴. 댓글 내용이 비어 있기에
         }
         
         // Ajax 요청에서 보낼 데이터 객체(object)를 생성.
         // object: { propertyName: propertyValue, ... }
         // 속성 값으로 사용할 변수 이름과 객체의 속성 이름을 같게 만들 때에는 {variable, ...}
         /*
         const data = {
             postId: postId,
             commentText: commentText,
             writer: writer,
         };
         */
        
        const data = {postId, commentText, writer}; 
        console.log(data);
        
        // Post 방식의 Ajax 요청을 보냄. 응답/실패 콜백을 등록.
        axios
        // post 방식의 Ajax 요청으로 데이터를 보낸다.
        .post('../api/comment', data)
        // 성공 응답이 왔을 때 실행할 콜백을 등록.
        .then((response) => {
            console.log(response);
            if(response.data === 1) {
                alert('댓글 등록 성공...!');
                // 댓글 입력 testarea의 내용을 비움.
                document.querySelector('textarea#commentText').value='';
            }
        })
        // 실패 응답일 때 실행할 콜백 등록.
        .catch((error) => {
            console.log(error);
        });
     } // end function registerComment
     
     /*
     * 현재 상세보기 페이지의 포스트에 달려 있는 모든 댓글 목록을 요청(Ajax).
     * 응답이 왔을 때 응답 처리.
     */
    function getAllComments(){
        // 댓글 목록을 요청하기 위한 포스트 번호(아이디):
        const postId = document.querySelector('input#id').value;
        
        // 댓글 목록을 요청할 URI (주소, 경로)
        const uri = `../api/comment/all/${postId}`;

        axios.get(uri) // GET 방식의 Ajax 요청을 보냄.
        .then((response) => {
            console.log(response);
            // TODO: 댓글 목록 html을 작성 -> 댓글 목록을 브라우저 화면에 보여줌.
            
        }) // 성공 응답이 왔을 때 실행할 콜백 등록
        .catch((error) => {
            console.log(error);
        }); // 요청 실패일 때 콜백 등록
        
    } // end function getAllComments
     
 });