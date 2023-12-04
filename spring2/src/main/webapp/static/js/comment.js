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
     console.log(bsCollapse);
 });