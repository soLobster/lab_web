/**
 * post_modify.js
 * 
 * /post/modify.jsp에 포함
 * 
 * 포스트 삭제와, 업데이트 기능.
 */
// function () {} == () => {}
document.addEventListener("DOMContentLoaded", () => {
    // form 요소를 찾음.
    const form = document.querySelector('form#postModifyForm');
    // 글 번호(id)를 가지고 있는 요소를 찾음.
    const inputId = document.querySelector('input#id');

    // 포스트 제목(title)을 가지고 있는 요소를 찾음.
    const inputTitle = document.querySelector('input#title');

    // 포스트 내용(content)를 가지고 있는 요소를 찾음.
    const textAreaContent = document.querySelector('textarea#content');

    // 삭제 버튼 찾기.
    const btnDelete = document.querySelector('button#btnDelete');

    // 수정 완료 버튼 찾기.
    const btnUpdate = document.querySelector('button#btnUpdate');

    // 삭제 버튼의 클릭 이벤트 핸들러(리스너)를 등록
    btnDelete.addEventListener('click', () => {
        const result = confirm('정말 삭제할까요?');
        // console.log(`confirm result = ${result}`); // -> true/false
        console.log(`confirm result = ${result}`);

        if (result) { // result === ture: 사용자가 확인을 선택하면 
            location.href = `delete?id=${inputId.value}`; // 상대경로(현재위치에서 delete를 실행) delete 요청을 보냄.
        } // delete?id='아이디번호'
    });

    // 수정 완료 버튼의 클릭 이벤트 리스너를 등록
    // JS가 form을 어떻게 submit 하느냐
    btnUpdate.addEventListener('click', () => {
        // 제목, 내용이 비어있는 지 체크:
        // value 만 사용. innerHTML 사용 X
        if (inputTitle.value === '' || textAreaContent.value === '') {
            alert('제목과 내용은 반드시 입력해야 합니다.');
            return; // 함수 종료
        }

        const result = confirm('정말 수정할까요?');

        if (result) {
            form.action = 'update'; // 폼 양식을 제출(submit)할 주소. 기본 값은 현재 URL.
            form.method = 'post'; // 폼 요청 방식 get or post. 기본값은 'get'.
            form.submit(); // 폼 제출(서버로 요청 보냄)
        }

    });


});