/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
    
    const btnSubmit = document.querySelector('#btnSubmit');
    
    btnSubmit.addEventListener('click', (e) => {
        e.preventDefault();
        
        const title = document.querySelector('#title').value;
        const content = document.querySelector('#content').value;
        const author = document.querySelector('#author').value;
        
        console.log('title = ' + title,', content = ' + content,', author = ' + author);
        
        if(title == ''){
            alert('제목을 입력하세요');
            return;
        }
        
        if(content == ''){
            alert('내용을 입력하세요')
            return;
        }
        
        const data = {title, content, author}
        console.log(data);
        
        const result = confirm('정말 등록 할까요...?');
        
        console.log(`confirm result = ${result}`);
        
        if(result) {
            axios.post('../api/post/create', data)
            .then((response) => {
                console.log(response);
                if(response.status === 200){
                    alert('POST 등록 완료...!');
                    console.log(response.data);
                    window.location.href = response.headers.location;
                }
            }).catch((error) => {
                console.log(error);
            });   
        }
        
    });
});
