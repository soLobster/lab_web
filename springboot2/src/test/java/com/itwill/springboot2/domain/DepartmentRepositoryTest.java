package com.itwill.springboot2.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository deptDao;
    
    @Test
    public void test() {
        List<Department> dList = deptDao.findAll();
        for(Department d : dList) {
            log.info(d.toString());
        }
    }
    
}
