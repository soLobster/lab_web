/**
 * object.js
 * 
 * 10_object.html에 포함
 */


document.addEventListener('DOMContentLoaded', () => {
    //JSON (JavaScript Object Notation): 자바스크립트 객체 표기법
    //{ property: value, ....}

    const person = {
        name: '오쌤',
        age: 16,
        phones: ['010-0000-0000', '02-0000-0000'],
    };
    console.log(person);

    // 객체가 가지고 있는 프로퍼티 접근(사용): 1) 참조 연산자, 2) 인덱스 연산자.
    console.log(person.name);// 참조 연산자.
    console.log(person['age']);// 인덱스 연산자.
    console.log(person.phones[0]);

    person.name = '홍홍홍';
    console.log(person);

    // for-in 구문에서 객체를 사용할 수 있음.
    for (let x in person) {
        console.log(x, ':', person[x]);
    }

    // 자바스크립트 객체는, 객체가 만들어진 이후에 프로퍼티들을 추가할 수 있음.
    person.email = 'honghonghon@itwill.com';
    console.log(person);

    // 메서드를 갖는 객체:
    const score = {
        html: 100,
        css: 50,
        js: 90,

        sum: function() {
            //return score.html + score.css + score.js;
            //프로퍼티를 참조할 때 this를 생략할 수 없음.
            return this.html + this.css + this.js;
        },

        mean: function() {
            //return score.sum/3;
            return this.sum() / 3;
        },
    };//const score

    console.log('sum = ', score.sum());
    console.log('mean = ', score.mean());
    
    // 생성자 함수(constructor function): this 키워드를 가지고 있는 함수.
    function Score(html, css, js){
        // 필드
        this.html = html;
        this.css = css;
        this.js = js;
        
        // 메서드
        this.sum = function () {
            return this.html + this.css + this.js;
        };
        
        this.mean = function () {
            return this.sum()/3;
        };
    } 
    
    // 생성자 함수를 호출할 때는 new 키워드를 사용!
    const score1 = new Score();
    console.log(score1);
    console.log(score1.sum());
    console.log(score1.mean());
    
    const score2 = new Score(100,90,98);
    console.log(score2.sum());
    console.log(score2.mean());
     
});