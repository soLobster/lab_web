/**
 * function.js
 * 08_function.html에 포함.
 */



/*
 
JS에서 함수를 만드는 방법.
function 함수이름([파라미터 선언, ...., ....]) {
    실행 코드;
    [return [반환값];]
}; 
 
 
함수의 리턴 타입을 선언하지 않음.
파라미터를 선언할 때는 const, let, var 키워드를 사용하지 않음.
 
*/

function add(x, y) {
    return x + y;
}

let result = add(1, 20); // 함수 호출

console.log('result = ', result);

// 자바스크립트의 함수는 파라미터의 타입을 검사하지 않음.

result = add('hello', ' javascript');
console.log('result = ', result);

result = add(2023, ' JS');
console.log('result = ', result);

console.log(typeof(add(2023, 'JS'))); // -> String

console.log(typeof(add(2023, 11))); // -> Number 

console.log(typeof(add('2023', 'JS'))); // -> String
