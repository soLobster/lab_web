package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.service.DepartmentSevice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentSevice deptService;
    
    @GetMapping("/list")
    public void deptList(Model model) {
        log.info("deptList()");
        
        List<Department> departments = deptService.getDeptList();
        model.addAttribute("departments", departments);
    }// end deptList()...
    
    @GetMapping("/details/{id}")
    public String deptDetails(@PathVariable(name = "id") Integer id, Model model) {
        log.info("deptDetails()");
        
        Department department = deptService.getDept(id);
        model.addAttribute("department", department);
        
        return "department/details";
    }
    
}
