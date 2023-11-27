package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

//POJO(Plain Old Java Object): 평범한 자바 객체.
//특정 클래스를 상속(extends) 하거나, 특정 인터페이스를 구현(implements)할 필요가 없는 
//(상위 타입의 특정 메서드를 반드시 재정의할 필요가 없는) 평범한 자바 객체.
//스프링 프레임워크는 POJO로 작성된 클래스를 컨트롤러로 사용할 수 있음.
//(비교) Servlet을 상속 받는 클래스에서는 doGet(req, resp) 또는 doPost(req, resp)를 반드시 override


@Slf4j // private static final Logger log  변수를 선언하고 초기화를 해줌.
@Controller // -> 디스패쳐 서블릿에게 컨트롤러 컴포넌트임을 알려줌.
// -> 디스패처 서블릿이 객체를 생성, 관리 - 필요할 때 메서드를 호출.
public class ExampleController {

    @GetMapping("/") // -> Context Root로 GET 방식의 요청이 왔을 때 호출되는 메서드.
    public String home(Model model) {
        log.debug("home()");        
        
        LocalDateTime now = LocalDateTime.now(); // 현재 시간 정보.
        model.addAttribute("now",now);
        
//      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//      <property name="prefix" value="/WEB-INF/views/"></property>
        return "home"; // -> /WEB-INF/views/home.jsp
        // 컨트롤러 메서드가 문자열을 리턴하면 디스패쳐 서블릿이 뷰의 이름을 찾는데 사용.
        
//      <property name="suffix" value=".jsp"></property>
//      </bean>
    }
    
    @GetMapping("/ex1")
    public void example1() {
        log.debug("example1");
        // 컨트롤러 메서드가 void인 경우(리턴값이 없는 경우) 
        // 요청 주소가 뷰의 경로(이름)이/가 된다.
    }
    
    @GetMapping("/ex2")
    public void example2(@RequestParam(name = "username") String username, 
            @RequestParam(name = "age" , defaultValue = "0") int age) {
        log.debug("example_2(userName={}, age={})", username, age);
        
        
    }
    
}
