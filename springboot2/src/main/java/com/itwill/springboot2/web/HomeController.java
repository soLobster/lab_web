package com.itwill.springboot2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.DepartmentRepository;
import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.domain.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private EmployeeRepository empDao;
    
    @Autowired
    private DepartmentRepository deptDao;
    
    @GetMapping("/")
    public String home() {
        log.info("home()");    
        
        return "index"; // templates/index.html
    }
    
    @GetMapping("/employee/list")
    public String eList(Model model) {
        log.info("eList()");
        
        List<Employee> employees = empDao.findAll();
        
        model.addAttribute("employees", employees);
        
        return "employee/list"; // templates/employee/list.html
    }
    
    @GetMapping("/employee/details/{id}")
    public String empDetails(@PathVariable(name = "id") Integer id, Model model) {
        log.info("empDetails(id = {})", id);
        
        Employee employee = empDao.findById(id).orElse(null);
        
        model.addAttribute("employee", employee);
        
        return "employee/details"; // templates/employee/details.html
    }
    
    @GetMapping("/department/list")
    public String dList(Model model) {
        log.info("dList()");
        
        List<Department> departments = deptDao.findAll();
        
        model.addAttribute("departments", departments);
        
        return "department/list"; // templates/department/list.html
    }
    
    @GetMapping("/department/details/{id}")
    public String deptDetails(@PathVariable(name = "id") Integer id, Model model) {
        log.info("deptDetails");
        
        Department department = deptDao.findById(id).orElse(null);
        
        model.addAttribute("department", department);
        
        return "department/details"; // templates/department/details.html
    }
}
