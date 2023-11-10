/**
 * array_fn.js 
 * 09_array_fn.html에 포함.
 * 
 */

document.addEventListener('DOMContentLoaded', function() {
    let numbers = []; // 빈배열

    // numbers 배열에 정수 1-10까지 차례로 저장.
    for (let i = 1; i < 11; i++) {
        //numbers.push(i);
        //-> push는 원본 배열에 원소를 추가. 원본 배열이 변경.
        numbers = numbers.concat(i);
        //-> concat은 원본 배열을 변경하지 않음! 새 원소가 추가된 새로운 배열을 만들어서 리턴.
    }

    console.log(numbers);

    // numbers에서 홀수들만 찾아서 저장하는 배열을 만들고 , 콘솔 로그 출력.
    let odds = [];
    for (let x of numbers) {
        if (x % 2) { // x%2 === 1 truly 
            odds = odds.concat(x);
        }
    };
    console.log('for문 odds = ', odds);


    let numbersOdd = numbers.filter(function(num) {
        return num % 2;
    });
    //let numbersOdd = numbers.filter(num => num % 2 !== 0);
    console.log(numbersOdd);
    // numbers 원소들의 제곱을 저장하는 배열을 만들고, 콘솔 로그 출력.
    let numSq = [];
    
    for (let x of numbers) {
        numSq = numSq.concat(x ** 2); // 거듭제곱 연산자
    };
    
    console.log('numSq = ', numSq);

    numSq = numbers.map((x) => x ** 2);
    console.log('map numSQ= ', numSq);

    let numbersSquare = [];
    numbers.forEach(num => numbersSquare.push(num ** 2));
    console.log(numbersSquare);
    // numbers에서 홀수들의 제곱을 저장하는 배열을 만들고, 콘솔 로그 출력.
    let oddSquare = numbers.filter((x) => x % 2).map((x) => x ** 2);
    console.log('oddSquare', oddSquare);

    let numbersOddSquare = [];
    numbersOdd.forEach(num => numbersOddSquare.push(num ** 2));
    console.log(numbersOddSquare);
});