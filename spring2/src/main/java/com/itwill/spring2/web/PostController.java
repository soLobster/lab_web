package com.itwill.spring2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.spring2.domain.Post;
import com.itwill.spring2.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post") // -> get/post를 모두 처리 할 수 있다.
// -> PostController의 컨트롤러 메서드의 매핑 URL(주소)는 "/post"로 시작
public class PostController {

    @Autowired
    private PostService postService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

    @GetMapping("/list") // -> Get 방식의 "/post/list" 요청 주소를 처리하는 메서드.
    public void list(Model model) {
        log.debug("list()");

        // postService의 메서드를 호출해서 포스트 목록을 만들고, 뷰에 전달.
        List<Post> postList = postService.read();
        model.addAttribute("postList", postList);

        log.debug(postList.toString());

        // 리턴 값이 없으면 요청 경로로 JSP를 찾음.
        // WEB-INF/views/post/list.jsp
    }
}
