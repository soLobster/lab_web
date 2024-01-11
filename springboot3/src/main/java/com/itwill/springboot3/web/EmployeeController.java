package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService empService;
    
//    // 전체 직원을 가져옴
//    @GetMapping("/list")
//    public void empList(Model model) {
//        log.info("empList()");
//        
//        List<Employee> employees = empService.getEmployeeList();
//        model.addAttribute("employees", employees);
//    }// end empList Method...
    
    // 전체 직원을 페이징으로 가져옴.
    @GetMapping("/list")
    public void empList(@PageableDefault Pageable pageable, Model model) {
        log.info("empList()");
        
        Page<Employee> empList = empService.getEmployeeList(pageable);
        
        log.info("NUMBER = {}", empList.getNumber());
        log.info("SIZE = {}", empList.getSize());
        log.info("TOTALPAGES = {}", empList.getTotalPages());
        
        model.addAttribute("employees" , empList);
    }
    
    @GetMapping("/details/{id}")
    public String empDetails(@PathVariable(name = "id") Integer id, Model model) {
        log.info("empDetails");
        
        Employee employee = empService.getEmployee(id);
        model.addAttribute("employee", employee);
        
        return "employee/details";
    }// end empDetails Method...
    
}
