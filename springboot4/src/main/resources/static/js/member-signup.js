/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {

    const btnNameCheck = document.querySelector('input#usernameCheck');

    btnNameCheck.addEventListener('click', usernameCheck);

    function usernameCheck(){
        const username = document.querySelector("input#SignUpId").value;

        if (username === "") {
            alert("아이디를 입력해주세요!. 필수항목입니다.");
            document.getElementById("username").focus();
            return;
        }
    }

});
