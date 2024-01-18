/**
 * 댓글 기능을 위한 JS 파일
 */

document.addEventListener('DOMContentLoaded', () => {
    console.log('comments js...');

    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {
        toggle: false  // 토글의 기본값. 열린 상태일지 닫힌 상태일지
    });

    // Collapse 객체 토글 버튼을 찾고 클릭 이벤트 리스너를 등록
    const btnToggleCollapse = document.querySelector('button#btnToggleCollapse');

    btnToggleCollapse.addEventListener('click', () =>{ // 익명 함수
        bsCollapse.toggle(); // 감추기 -> 보기

        if(btnToggleCollapse.innerHTML === '댓글 보기'){
            btnToggleCollapse.innerHTML = '가리기';
            getAllComments(); // 댓글 목록 갱신
        } else {
            btnToggleCollapse.innerHTML = '댓글 보기';
        }
    });

    // 댓글 등록 버튼의 이벤트 리스너
    const btnRegComment = document.querySelector('button#btnRegComment');

    btnRegComment.addEventListener('click', registerComment); // 함수 이름 -> registerComment

    // ---- 함수 선언 ----
    // 버튼의 클릭 이벤트 리스너.
    // 댓글 등록 Ajax 요청을 보내고, 응답을 받으면 댓글 목록을 갱신하는 비동기 함수.

    async function registerComment() {
        // 댓글이 달리는 포스트의 아이디
        const postId = document.querySelector('input#postId').value;
        // 댓글 내용
        const ctext = document.querySelector('textarea#cText').value;
        // 댓글 작성자
        const writer = document.querySelector('input#cWriter').value;

        if(ctext === ''){
            alert('내용을 입력하셔야 합니다...!');
            return;
        }

        // Ajax 요청에 포함시켜서 보낼 데이터를 JS 객체로 생성
        const data = {postId, ctext, writer};

        console.log(data);

        try {
            // Ajax POST 요청을 보냄. await가 있다면 함수 키워드 앞에 async를 붙인다.
            const response = await axios.post('/api/comment', data);
            console.log(response);
            document.querySelector('textarea#cText').value = '';
            alert('댓글 등록을 성공했습니다...!');

            // 댓글 목록 갱신
            getAllComments();


        } catch (error) {
            console.log(error);
        }
    }; // end function registerComment()

    // 포스트 details 페이지에서, 포스트에 달려 있는 모든 댓글 목록을 요청, 응답을 처리.
    // 댓글 목록 Collapse 객체를 펼칠 때, 댓글 등록이 성공했을 때.
    // 댓글 목록을 갱신하기 위해서 호출.

    async function getAllComments() {
        const postId = document.querySelector('input#postId').value;
        const uri = `/api/comment/all/${postId}`;
        try {
            const response = await axios.get(uri);
            console.log(response);

            makeCommentElements(response.data);

        } catch(error) {
            console.log(error);
        }

    }; // end function getAllComments()

    // 댓글들의 배열을 아규먼트 data로 전달 받아서, html 코드를 div에 추가
    function makeCommentElements(data){
        const commentDiv = document.querySelector('div#commentDiv'); // 댓글을 추가할 영역 div
        let htmlStr = '' ; // div에 삽입할 html 코드
        for (let comment of data){
            const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric', timeZone: 'Asia/Seoul' };
            const time = new Date(comment.modifiedTime).toLocaleString('ko-KR', options);
            htmlStr += `
             <div class = "card card-body my-1" data-id = "${comment.id}">
             
                 <div class = "d-grid gap-2 justify-content">
                    <span class = "d-none">${comment.id}</span>
                    <span class = "fw-bold fs-4">${comment.writer}</span>
                    <span class = "text-secondary">${time}</span>
                 </div>
                 <hr class = "border-1">
                 <div>
                 <textarea class = "tArea form-control" readonly>${comment.ctext}</textarea>
                 </div>
                 <hr class = "border-1">
                 <div class="d-grid gap-1 d-md-flex justify-content-md-end">
                    <button class="btnCommentDelete btn btn-outline-danger btn-sm me-md-2" data-id="${comment.id}">삭제</button>
                    <button class="btnCommentModify btn btn-outline-primary btn-sm" data-bs-toggle="collapse" 
                    data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample" 
                    data-id="${comment.id}">수정</button>
                 </div>
                 <div class="collapse mt-1" id="collapseExample-${comment.id}" data-id = ${comment.id}>
                 <!-- 수정된 부분: 각 댓글에 대한 고유한 ID를 사용하여 textarea와 창닫기 버튼 추가 -->
                    <textarea class="form-control my-2" id="updateTextarea-${comment.id}" data-id="${comment.id}"></textarea>
                    <div class="d-grid gap-1 d-md-flex justify-content-md-end">
                        <button class="closeModify btn btn-secondary btn-sm" id="closeModify-${comment.id}" data-id="${comment.id}">창 닫기</button>
                    </div>
                 </div>
             </div>
             <hr>
             `;
        }
        commentDiv.innerHTML = htmlStr;
        // 모든 삭제 버튼을 찾아서 클릭 이벤트 리스너를 등록
        const btnDeletes = document.querySelectorAll('button.btnCommentDelete');
        for (let btn of btnDeletes) {
            btn.addEventListener('click', (e) => {
                const result = confirm('댓글을 삭제하겠습니까??');

                if(!result){
                    return;
                }

                const id = e.target.getAttribute('data-id');
                console.log(id);

                const uri = `/api/comment/${id}`;
                try {
                    const response = axios.delete(uri);
                    alert('댓글을 삭제했습니다.');
                    getAllComments();
                    console.log(response);
                }catch(error){
                    console.log(error);
                }
                //alert('삭제 버튼 입니다...!!!');
            });
        }

        //  모든 수정 버튼을 찾아서 클릭 이벤트 리스너를 등록
        let btnModifies = document.querySelectorAll('button.btnCommentModify');
        for (let btn of btnModifies){
           
            btn.addEventListener('click', (e) => {
                
            e.preventDefault();
            
                const commentId = e.target.getAttribute('data-id');
                console.log('comment id = '+ commentId);
                
                const collapseExample = document.querySelector(`#collapseExample-${commentId}`);
                console.log('collapseExample = ' + collapseExample.getAttribute('data-id'));
                
                const btnCloseModify = document.querySelector(`button#closeModify-${commentId}`);
                console.log(btnCloseModify);
                const textarea = document.querySelector(`textarea#updateTextarea-${commentId}`);
                
                btnCloseModify.addEventListener('click', () => {    
                    collapseExample.className = 'collapse';
                    btn.innerHTML = '수정';
                    textarea.value = '';
                });
                
                if(btn.innerHTML === '수정'){
                    
                    btn.innerHTML = '수정 확정';
                    collapseExample.className = 'collapse show';
                    
                } else if (btn.innerHTML === '수정 확정') {
                  const ctext = document.querySelector(`textarea#updateTextarea-${commentId}`).value;

                    if(ctext == ''){
                        alert('수정할 댓글을 입력하셔야 합니다.');
                        collapseExample.className = 'collapse show';
                        btn.innerHTML = '수정 확정';
                        return;
                    }
                    
                    const result = confirm('댓글을 수정 하겠습니까??');
                    // 버튼을 collapse로 했기에 누르면 닫힌다.
                    // 업데이트를 수행한다. 그 다음에 div를 비워야함.
                    const id = e.target.getAttribute('data-id');
                    console.log(id);
                    
                    if(!result) {
                        collapseExample.className = 'collapse show';
                        btn.innerHTML = '수정 확정';
                        return;
                    }
                    
                    const textarea = document.querySelector(`textarea#updateTextarea-${commentId}`);

                    const data = {ctext};

                    console.log(data);

                    try{
                        const response = axios.put(`/api/comment/${id}`, data);
                        btn.innerHTML = '수정';
                        textarea.innerHTML = '';
                        alert('댓글을 수정 하였습니다.');
                        getAllComments();
                    } catch (error) {
                        console.log(error);
                    }
                }// if...else if
 
           });// end btn addEventListener
           
        } // end for btn
        
    }; // end function makeCommentElements
    
});