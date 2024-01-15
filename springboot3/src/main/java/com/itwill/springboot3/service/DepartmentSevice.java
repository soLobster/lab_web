package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentSevice {

    private final DepartmentRepository deptDao;
    
    // 부서 전체 목록을 가져옴
    public List<Department> getDeptList(){
        log.info("==================");
        log.info("getDeptList()");
        log.info("==================");
        
        return deptDao.findAll();
    }
    
    public Department getDept(Integer id) {
        log.info("==================");
        log.info("getDept(DEPT_ID = {})",id);
        log.info("==================");
        
        return deptDao.findById(id).orElse(null);
    }
}
