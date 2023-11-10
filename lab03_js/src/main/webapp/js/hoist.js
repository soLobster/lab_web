/**
 * hoist.js
 * 
 * 08_function.html에 포함.
 * 
 * 호이스팅: function 키워드를 사용한 선언은 스크립트에서 먼저 실행된다.
 * 
 * function 키워드를 사용해서 선언한 (이름이 있는)함수는 언제든지 호출할 수 있다.
 *  - 스크립트 파일에서 함수 호출 코드가 먼저 작성되고, 함수 선언이 나중에서 작성되고 됨.
 * 
 * 함수 객체를 저장하는 (지역) 변수 이름으로 함수를 호출할 때는 반드시 변수가 선언된 다음에만 가능.
 *  
 */

function test1() {
    console.log('test1')
}

test1();

test2(); // -> function으로 선언된 함수는 언제든지 호출 가능.
// -> 함수 선언보다 먼저 함수 호출이 가능. why? function 키워드가 호이스팅 되기 때문.


function test2() {
    console.log('test2');
}

// test3(); // -> ReferenceError: Cannot access 'test3' before initialization 
// 변수 선언(초기화) 이전에 호출하면 안된다. 

const test3 = function() {
    console.log('test3');
}

test3();

const plus = (x, y) => x + y;

console.log(plus(1,2));

var x = 1;
function testVar(x) {
    console.log('x= ', x);
    
    var x = 10;
    console.log('x= ', x);
}

testVar(x);
console.log('x = ', x);


