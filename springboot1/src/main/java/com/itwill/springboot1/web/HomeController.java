package com.itwill.springboot1.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.itwill.springboot1.dto.Author;
import com.itwill.springboot1.dto.Book;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/") // context root로 들어오는 GET방식의 요청을 처리하는 메서드
    public String home(Model model) { 
        log.info("Home()");
        
        LocalDateTime nowTime = LocalDateTime.now();
        
        model.addAttribute("currentTime", nowTime);
        
        Author author = Author.builder().firstName("약용").lastName("정").build();
        
        Book book = Book.builder().id(100).title("목민심서").author(author).build();
        
        model.addAttribute("book", book);
        
        return "home"; // -> 뷰의 이름 home.html
    }
    
    // 질의 문자열의 요청 파라미터를 처리하는 컨트롤러 메서드:
    @GetMapping("/book")
    public void bookDetails(@RequestParam(name = "id") int id, Model model) {
        // 로그 레벨 -> trace < debug < info < warning < error < fatal
        // 스프링 부트에서 기본 로그 출력 레벨은 info 이상.
        log.info("bookDetails(id = {})", id);
        
        Book book = Book.builder().id(id).title("책_" + id).build();
        
        model.addAttribute("book", book);
        // return 값이 없는 경우 view의 이름은 요청 주소가 된다(book.html).
    }
    
    // 경로 변수(path variable)을 처리하는 컨트롤러 메서드:
    @GetMapping("/book/{id}")
    public String bookDetails2(@PathVariable(name = "id") int id , Model model) {
        log.info("bookDetails2(id = {})", id);
        
        Book book = Book.builder().id(id).title("BOOK_" + id).build();
        model.addAttribute("book", book);
        
        return "book";
    }
    
    @GetMapping("/books/list")
    public String booksList(Model model) {
        log.info("booksList()");
        
        // 더미 데이터를 저장하기 위한 리스트 객체 생성
        List<Book> books = new ArrayList<>();
        
        for(int i = 1; i<=10; i++) {
            Author author = Author.builder().firstName("first_").lastName("last" + i).build();
            books.add(Book.builder().id(i).title("Book_" + i).author(author).build()); 
        }
        // 더미 데이터를 모델에 실어서 뷰에게 전달
        model.addAttribute("books", books);
        // 리턴 타입이 void인 경우 -> templates/books/list.html
        // return "list"; // 뷰 -> templates/list.html
        // return "books/list"; // 뷰 -> templates/books/list.html
        return "list";
    }
}
