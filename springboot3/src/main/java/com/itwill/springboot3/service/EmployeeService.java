package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.domain.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository empDao;
    
    // 전체 직원을 가져옴
    public List<Employee> getEmployeeList(){
        log.info("==================");
        log.info("getEmployeeList()");
        log.info("==================");
        
        return empDao.findAll();
    }
    
    public Employee getEmployee(int id) {
        log.info("==================");
        log.info("getEmployee()");
        log.info("==================");
        
        return empDao.findById(id).orElse(null);
    }
    
    public Page<Employee> getEmployeeList(Pageable pageable) {
        log.info("==================");
        log.info("getEmployeeList()");
        log.info("==================");
        
        log.info("getPageNumber = {}", pageable.getPageNumber());
        
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "id"));

        return empDao.findAll(pageable);
    }
}
