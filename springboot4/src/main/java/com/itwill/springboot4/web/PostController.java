package com.itwill.springboot4.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot4.domain.Post;
import com.itwill.springboot4.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    
//    @GetMapping("/list")
//    public void getPostList(Model model) {
//        log.info("getPostList()");
//        
//        List<Post> postList = postService.getPostList();
//        
//        model.addAttribute("postList", postList);
//    }
    
    @GetMapping("/list")
    public void postList(@RequestParam(name = "p", defaultValue = "0") int p, Model model) {
        log.info("list(p={})", p);
        
        Page<Post> data = postService.getPostList(p);
        
        model.addAttribute("page", data);
        log.info(model.toString());
    }
    
    
    @GetMapping("/details/{id}")
    public String getPostDetails(@PathVariable (name = "id") long id, Model model) {
        log.info("getPostDetails()");
        
        Post post = postService.getPostDetails(id);
        
        model.addAttribute("post",post);
        
        return "post/details";
    }
   
    @GetMapping("/update/{id}")
    public String getPostUpdate(@PathVariable (name = "id") long id, Model model) {
        log.info("getPostUpdate()");
        
        Post post = postService.getPostDetails(id);
        
        model.addAttribute("post", post);
        
        return "post/update";
    }
    
    @GetMapping("/create")
    public String postCreate() {
        log.info("create Post ()");
        
        return "post/create";
    }
}
