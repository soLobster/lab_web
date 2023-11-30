package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.dto.post.PostCreateDto;
import com.itwill.spring2.dto.post.PostListItemDto;
import com.itwill.spring2.service.PostService;

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
    
    @GetMapping("/create")
    public void create() {
        log.debug("Get create()");
    }
    
    @PostMapping("/create")
    public String create(PostCreateDto dto) {
        log.debug("Post create(PostCreateDto dto) = {}", dto);
        
        // 서비스 계층의 메서드를 호출해서 새 포스트 작성 서비스를 수행.
        postService.create(dto);
        
        return "redirect:/post/list"; // list 로 이동(redirect)
    }
}
