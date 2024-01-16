/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
    
    const btnUpdate = document.querySelector('#btnUpdate');
    
    const btnDelete = document.querySelector('#btnDelete');
    
    btnUpdate.addEventListener('click', (e) => {
        e.preventDefault();
        
        const id = document.querySelector('#id').value;
        const title = document.querySelector('#title').value;
        const content = document.querySelector('#content').value;
        const author = document.querySelector('#author').textContent;
        
        console.log('id = ' + id,', title = ' + title,', content = ' + content,', author = ' + author);
        
        if(title == ''){
            alert('제목을 입력하세요');
            return;
        }
        
        if(content == ''){
            alert('내용을 입력하세요')
            return;
        }
        
        const data = {title, content}
        console.log(data);
        
        const result = confirm('정말 수정 할까요...?');
        
        console.log(`confirm result = ${result}`);
        
        if(result) {
            axios.put(`/api/post/update/${id}`, data)
            .then((response) => {
                console.log(response);
                if(response.status === 200){
                    alert('POST 수정 완료...!');
                    window.location.href = response.headers.location;
                }
            }).catch((error) => {
                console.log(error);
            });   
        }
        
    });
    
    btnDelete.addEventListener('click',(e) => {
        e.preventDefault();
        const id = document.querySelector('#id').value;
        const title = document.querySelector('#title').value;
        const content = document.querySelector('#content').value;
        const author = document.querySelector('#author').textContent;
        
        const result = confirm('정말 삭제 할까요...?');
        
        console.log(id, title, content, author);
        
        console.log(result);
        
        if(result){
            axios.delete(`/api/post/delete/${id}`)
            .then((response) => {
                console.log(response);
                if(response.status === 200){
                    alert('POST 삭제 완료....!');
                    window.location.href = response.headers.location;
                }
            }).catch((error) => {
                console.log(error);
            });
        }
    });
    
});
