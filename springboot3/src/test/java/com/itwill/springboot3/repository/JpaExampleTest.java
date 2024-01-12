package com.itwill.springboot3.repository;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.springboot3.domain.Country;
import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.domain.EmployeeRepository;
import com.itwill.springboot3.domain.Location;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaExampleTest {
    
    @Autowired
    private EmployeeRepository empDao;
    
//    @Test
//    public void test() {
//        // lastName이 King인 직원(들)의 정보 검색.
//        
//        // Example이란 기능을 사용해본다.
//        // Generic 클래스
//        Employee emp = new Employee();
//        // Employee 엔터티에는 @RequiredArgsConsturctor가 있기에 빈 생성자 -> final 변수가 없기에
//        // 빈 emp Employee 객체에 LastName을 set을 해준다.
//        emp.setLastName("King");
//        // LastName만을 이용해서 DB에서 비교를 하고 리스트를 가져온다.
//        Example<Employee> example = Example.of(emp) ;
//        
//        List<Employee> list = empDao.findAll(example);
//        
//        for(Employee e : list) {
//            log.info("e = {}", e.toString());
//        }
        
       //@Test
       public void test2() {
           // 부서 이름이 "IT"인 직원들의 정보를 Example을 사용해서 검색.
           Employee emp = new Employee();
           
           Department dept = new Department();
           
           dept.setName("Sales");
           
           emp.setDept(dept);
           
           Example<Employee> example = Example.of(emp);
           
           Pageable pageable = PageRequest.of(3, 10, Sort.by("id"));
           
           Page<Employee> list = empDao.findAll(example, pageable);
           list.forEach((e) -> log.info(e.toString()));
           // 페이지는 0부터 시작한다 
           log.info("number = {}", list.getNumber());
           // 현재 페이지의 데이터 수
           log.info("number of Elements = {}", list.getNumberOfElements());
           // 전체 데이터 수
           log.info("Total Elements = {}", list.getTotalElements());
           // TotalPages에 4라고 나온다 -> 0~3까지 4페이지임
           log.info("TOTALPAGES = {}", list.getTotalPages());
           // pageable 객체를 가져온다 [number(현재 페이지), size(한 페이지에 나오는 수) , sort 방식(ASC,DESC)]
           log.info("pageable?? = {}",list.getPageable());
           // Content 가져온 데이터 전체를 보여준다.
           log.info("Content?? ={}", list.getContent().toString());
           
//           Iterator<Employee> emps = list.getContent().iterator();           
//           while(emps.hasNext()) {
//               Employee emp1 = emps.next();
//               log.info("emp = {}",emp1);
//           }
           
//           List<Employee> list = empDao.findAll(example);
//           list.forEach((x) -> log.info("x = {}", x.toString()));
       }// end of test2
       
       @Test
       public void test3() {
           Employee emps = new Employee();
           Department depts = new Department();
           Location loc = new Location();
           Country nation = new Country();
           
           nation.setName("United States of America");
           loc.setCountry(nation);
           depts.setLocation(loc);
           emps.setDept(depts);
           
           Example<Employee> ex = Example.of(emps);
           
           Pageable pageable = PageRequest.of(0, 5 , Sort.by("id"));
           Page<Employee> list = empDao.findAll(ex, pageable);
           log.info("number of Elements = {}", list.getNumberOfElements());
           log.info("total Elements = {}", list.getTotalElements());
           log.info("TOTALPAGES = {}", list.getTotalPages());
           list.forEach((e) -> log.info("e = {}", e.toString()));
           
           
       }
}
