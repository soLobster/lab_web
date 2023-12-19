package com.itwill.spring2.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.post.PostCreateDto;
import com.itwill.spring2.dto.post.PostListItemDto;
import com.itwill.spring2.dto.post.PostUpdateDto;
import com.itwill.spring2.service.PostService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드들을 초기화하는 아규먼트를 갖는 생성자.
@Controller
@RequestMapping("/post") // -> get/post를 모두 처리 할 수 있다.
// -> PostController의 컨트롤러 메서드의 매핑 URL(주소)는 "/post"로 시작
public class PostController {

    // PostService 객체를 주입 받음.
    // 1) @ 애너테이션을 사용한 의존성 주입
    // @Autowired
    // private PostService postService;

    // 2) 생성자에 의한 의존성 주입
    private final PostService postService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

    @GetMapping("/list") // -> Get 방식의 "/post/list" 요청 주소를 처리하는 메서드.
    public void list(Model model) {
        log.debug("list()");

        // postService의 메서드를 호출해서 포스트 목록을 만들고, 뷰에 전달.
        List<PostListItemDto> postList = postService.read();
        model.addAttribute("postList", postList);
        // Model.addAttribute(String attributeName, @Nullable Object attributeValue) 
        // name => JSP 에서 쓰는 것.
        log.debug(postList.toString());

        // 리턴 값이 없으면 요청 경로로 JSP를 찾음.
        // WEB-INF/views/post/list.jsp
    }// end list method
    
    @GetMapping({"/details", "/modify"}) 
    // 문자열 배열로 여러가지 Mapping 가능(같은 기능이면) -> /post/details , /post/modify
    public void details(@RequestParam(name = "id") Long id, Model model) {
        log.debug("Get details(id = {})", id);
                
        Post post = postService.details(id);
                
        model.addAttribute("post", post);     
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam(name="id") Long id) {
        log.debug("Get Delete(id = {})",id);
        
        postService.delete(id);
        
        return "redirect:/post/list";
    }
    
    
    @GetMapping("/create")
    public void create() {
        log.debug("Get create()");
    }
    
    @GetMapping("/search")
    public String search(@RequestParam(name="category") String value, 
            @RequestParam(name="keyword") String keyword, Model model) {
        log.debug("Get Search()");
        
        List<PostListItemDto> list =  postService.search(value, keyword);
        
        log.debug("PostController-search = {}", list);
        
        model.addAttribute("postList", list);

        return "post/list";
    }
    
    @PostMapping("/update")
    public String update(PostUpdateDto dto) {
        log.debug("Post update(PostUpdateDto dto) = {}",dto);
        
        postService.update(dto);
        
        return "redirect:/post/list";
    }
    
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) 
            throws IllegalStateException, IOException {
        log.debug("Post create(PostCreate title) = {}",dto.getTitle());
        log.debug("Post create(PostCreate content) = {}", dto.getContent());
        log.debug("Post create(PostCreate author) = {}",dto.getAuthor());
        log.debug("Post create(MultipartFile original_file = {})", dto.getOriginal_file().getOriginalFilename());
        
        
        String UPLOAD_PATH = "/Users/ojng/lab_web/spring2/src/main/webapp/static/tmp/";
        
        
        if (!dto.getOriginal_file().isEmpty()) {

            String originalFileName = getFileName(dto.getOriginal_file());
            String fileExtension = getFileExtension(originalFileName);
            String fileName = UUID.randomUUID().toString() + fileExtension;

            String filePath = UPLOAD_PATH + fileName;
            log.debug("Post create(filePath = {})",filePath);
            
            String saved_file = fileName;
            log.debug("Post create(saved_file = {})", saved_file);
            
            // 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
            
            dto.setSaved_file(saved_file);
            
            postService.create(dto);

            File destFile = new File(filePath);

            dto.getOriginal_file().transferTo(destFile);

            log.debug("UPLOAD SUCCESS...!");

        }
        
        return "redirect:/post/list"; // list 로 이동(redirect)
    }
    
    
    private String getFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        
        originalFileName = originalFileName.substring(originalFileName.lastIndexOf("/")+1);
        
        return originalFileName;
    }
    
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if(dotIndex >= 0 && dotIndex < fileName.length() -1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }
    
    @GetMapping("/upload")
    public void upload() {
        log.debug("Get upload()");
    }
    
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        log.debug("Post Upload = {}", file);
        
        String UPLOAD_PATH = "/Users/ojng/lab_web/spring2/src/main/webapp/static/tmp/";
        
        if(!file.isEmpty()) {
            String originalFileName = getFileName(file);
            log.debug("OriginalFileName = {}",originalFileName);
            String fileExtension = getFileExtension(originalFileName);
            log.debug("fileExtension = {}", fileExtension);
            String fileName = UUID.randomUUID().toString() + fileExtension;
            log.debug("fileName = {}", fileName);
            String filePath = UPLOAD_PATH + fileName;
            log.debug("filePath = {}", filePath);
            File destFile = new File(filePath);
            
            file.transferTo(destFile);
          
            
            log.debug("UPLOAD SUCCESS...!");
        }
        
        
        
        
        return "redirect:/post/list";
    }
}
