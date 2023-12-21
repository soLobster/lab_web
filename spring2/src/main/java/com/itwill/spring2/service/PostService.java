package com.itwill.spring2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.post.PostCreateDto;
import com.itwill.spring2.dto.post.PostListItemDto;
import com.itwill.spring2.dto.post.PostUpdateDto;
import com.itwill.spring2.repository.PostDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service // 스프링 컨테이너에서 관리하는 서비스 컴포넌트.
public class PostService {
    // PostDao를 주입 받음.
//    @Autowired
//    private PostDao postDao;

    private final PostDao postDao;
    
//    public PostService(PostDao postDao) {
//        this.postDao = postDao;
//    }

    public List<PostListItemDto> read() {
        log.debug("read()");
        
        // postDao의 메서드를 호출해서 포스트 목록을 리턴받고,
        List<Post> list = postDao.selectOrderByIdDesc();

        log.debug("List = {}", list);
        log.debug("Number Of List = {}", list.size());

        // 컨트롤러에게 리턴해준다.

        return list.stream()
                .map(PostListItemDto::fromEntity) // map((x) -> PostListItemDto.fromEntity(x))
                .toList();
    }// end read() method
    
    public void create(PostCreateDto dto, String sDirectory) throws IllegalStateException, IOException {
        log.debug("PostService - create(dto = {})", dto);
        log.debug("PostService - create(sDirectory = {})", sDirectory);
        
        // Repository 계층의 메서드를 호출해서 테이블에 데이터 insert.
        
        MultipartFile file = dto.getOriginal_file();
        
        if (!dto.getOriginal_file().isEmpty()) {

            String originalFileName = file.getOriginalFilename();
            log.debug("originalFileName = {}",originalFileName);
            
            // 확장자...
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            log.debug("fileExtension = {}",fileExtension);
            
            // 새로운 파일 이름...
            String savedFileName = UUID.randomUUID().toString() + fileExtension;
            
            String absolutePath = sDirectory + File.separator + savedFileName;
            log.debug("파일 절대 경로 = {}", absolutePath);
            file.transferTo(new File(absolutePath));
            
            dto.setSaved_file(savedFileName);
            
            // 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
            postDao.insert(dto.toEntity());
            
            log.debug("UPLOAD SUCCESS...!");
        }
    }// end create(dto, saved_file)
    
    public int update(PostUpdateDto dto) {
        log.debug("PostService - update(dto={})",dto);
        
        int result = postDao.update(dto.toEntity());
        
        return result;
    }
    
    public int delete(long id) {
        log.debug("PostService - Delete(dto={})", id);
        
        int result = postDao.delete(id);
        
        return result;
    }
    
    public List<PostListItemDto> search(String value, String keyword){
        log.debug("PostService - search(category = {}, keyword = {})", value, keyword);
        
        List<Post> list = postDao.selectByCategory(value, keyword);
        
        return list.stream().map(PostListItemDto::fromEntity).toList();
    }
    
    public Post details(long id) {
        log.debug("PostService_details(id = {})", id);
        
        Post post = postDao.selectById(id);
        
        log.debug("PostService_details post = {}", post);
        
        return post;
    }// end details
}
