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
    console.log('x= ', x, ', y= ', y)
    return x + y;
};

let result = add(1, 20); // 함수 호출

console.log('result = ', result);

// 자바스크립트의 함수는 파라미터의 타입을 검사하지 않음.

result = add('Hello', ' Javascript');
console.log('result = ', result);

result = add(2023, 'JS');
console.log('result = ', result);

console.log(typeof (add(2023, 'JS'))); // -> String

console.log(typeof (add(2023, 11))); // -> Number 

console.log(typeof (add('2023', 'JS'))); // -> String

result = add(1, 2, 3); // -> 파라미터보다 많은 수의 아규먼트를 전달할 때에는 버린다.
console.log(result);

// JS 함수는 argument 속성을 가지고 있음.
// argument 속성은 함수를 호출하는 곳에서 전달한 모든 아규먼트들을 저장하느 객체

function testArgs() {
    console.log(arguments); //arguments 객체 - 배열.
    for (let x of arguments) {
        console.log(x);
    };
}

testArgs();
testArgs('안녕');
testArgs('안녕', 1, 2, 3, 'Hello');

/*
자바스크립트 함수는 객체(object)!
1. 객체는 자기만의 property(멤버)를 가질 수 있다. ex) arguments
2. 변수에 저장할 수 있다.
3. 다른 함수를 호출할 때 아규먼트로 전달 할 수 있음.
4. 함수 내부에서 다른 함수를 선언(정의)할 수 있음.
5. 함수 자체를 리턴할 수 있음.
*/


const plus = add; // 함수 객체 add를 변수 plus에 할당.
console.log(plus); // 함수 객체를 출력. 
console.log(plus(1, 5)); // 함수 호출 결과를 출력.

// 익명 함수를 선언하고, 변수에 저장.

const minus = function(x, y) {
    return x - y;
}; // 이런 형식을 더 많이 쓴다.

console.log('function.minus result = ', minus(10, 5));

// 함수를 아규먼트로 갖는 함수:

function calculate(x, y, op) {
    return op(x, y);
}

console.log('plus = ', calculate(1, 2, plus));

console.log('minus = ', calculate(1, 2, minus));

console.log('divide = ', calculate(1, 2, function(x, y) {
    return x / y;
}));

// 콜백(callback): (나중에 호출하기 위해서. )다른 함수의 아규먼트로 전달되는 함수.
// 이벤트 리스너(listener), 핸들러(handler), 콜백

function increase(n) {
    // 지역(내부) 함수: 함수 내부에서 선언하는 함수.
    function addN(x) {
        return x + n;
    }

    return addN; // 함수 객체를 리턴.
};

const increaseTwo = increase(2);

console.log('increaseTwo = ', increaseTwo(2));

const increaseFive = increase(5);

console.log('increaseFive = ', increaseFive(10));

console.log('increaseFive = ', increase(5)(10));

// 화살표 함수(arrow Function) 
// (파라미터, ....) => { 실행코드 ; }
// (파라미터, ....) => 리턴값;

const multiply = (x, y) => { return x * y };
const multiply2 = (x, y) => x * y;

console.log(multiply(2,3));

console.log(multiply2(3,4));






