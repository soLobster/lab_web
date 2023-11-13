/**
 * 13_event
 * 
 */

document.addEventListener('DOMContentLoaded', function(e) {
    console.log(e);

    // 1. button#btnInput 버튼에 클릭 이벤트 리스너를 등록:
    // input에 입력된 내용을 ul에 리스트 아이템으로 추가.

    const mouseInput = document.getElementById('itemInput');
    const btnInput = document.getElementById('btnInput');
    const List = document.getElementById('itemList');

    btnInput.addEventListener('click', function() {
        let list = document.createElement('li');

        if (mouseInput.value == "") {
            alert("아이템을 입력해주세요!");
        } else {
            list.innerText = mouseInput.value;
            List.append(list);
            mouseInput.value = "";
        }
        mouseInput.focus();
    });

    // 2. #itemInput2 요소에 'keydown' 이벤트리스너를 등록:
    // 엔터키가 눌렸을 때, input에 입력된 내용을 ul에 리스트 아이템으로 추가.

    const keyboardInput = document.getElementById('itemInput2');
    const List2 = document.getElementById('itemList2');

    keyboardInput.addEventListener('keydown', (e) => {
        let list = document.createElement('li');

        if (e.key == 'Enter' && !e.shiftKey) {
            if (keyboardInput.value != "") {
                list.innerText = keyboardInput.value;
                List2.append(list);
                keyboardInput.value = "";

            } else{
                alert("아이템을 입력해주세요..!!!");
            }
        }
    });


    // 3. #username 요소에 이벤트 리스너를 등록
    // input에 입력된 내용이 바뀔 때마다 div에 그때까지 입력된 내용을 씀.

    let keyboardInput2 = document.getElementById('username1');
    const resultName1 = document.getElementById('result1');

    keyboardInput2.addEventListener('keydown', (e) => {
        console.log(e);
        resultName1.innerText = keyboardInput2.value;

    });

    let keyboardInput3 = document.getElementById('username2');
    const resultName2 = document.getElementById('result2');

    keyboardInput3.addEventListener('input', (e) => {
        console.log(e);
        resultName2.innerText = keyboardInput3.value;

    });

    let keyboardInput4 = document.getElementById('username3');
    const resultName3 = document.getElementById('result3');

    keyboardInput4.addEventListener('change', (e) => { //포커스를 잃었을때 체인지 이벤트가 발생한다.
        console.log(e);
        resultName3.innerText = keyboardInput4.value;

        //resultName3.innerText = `사용자 이름: <strong> ${keyboardInput4.value}</strong>`;

    });


});