package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.domain.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaTest {

    @Autowired
    private EmployeeRepository empDao;
    
//    @Test
//    public void test1() {
//        Assertions.assertNotNull(empDao);
//        
//        List<Employee> list = empDao.findByDeptId(90);
//        
//        for(Employee e : list) {
//            log.info("e = {}",e.toString());
//        }
//    }
    
//    @Test
//    public void test2() {
//        List<Employee> list = empDao.findByDeptNameIgnoreCase("it");
//        
//        // lambda exp
//        list.forEach((emp) -> log.info("emp = {}",emp.toString()));
//        
////        for(Employee e : list) {
////            log.info("dept = {}", e.toString());
////        }
//    }
    
//    @Test
//    public void test3() {
//        List<Employee> list = empDao.findByLastName("King");
//        
//        list.forEach((x) -> log.info("x = {}", x.toString()));
//        
//    }
    
//     @Test
//     public void test4() {
//         List<Employee> list = empDao.findByLastNameContaining("ing");
//         
//         list.forEach((x) -> log.info("x = {}", x.toString()));
//     }

//       @Test
//       public void test5() {
//           List<Employee> list = empDao.findByLastNameContainingIgnoreCase("iNG");
//           
//           list.forEach((x) -> log.info("x = {}", x.toString()));
//       }
    
//    @Test
//    public void test6() {
//        List<Employee> list = empDao.findByLastNameContainingIgnoreCaseOrderByLastName("ING");
//        
//        list.forEach((x) -> log.info("x = {}" , x.toString()));
//    }
    
    @Test
    public void test7() {
        //List<Employee> list = empDao.findBySalaryLessThan(3100D);
        //List<Employee> list = empDao.findBySalaryGreaterThan(7000D);
        //List<Employee> list = empDao.findBySalaryBetween(3000D, 7000D);
        //LocalDate hireDate2 = LocalDate.of(2007, 01, 01);
        LocalDate hireDate1 = LocalDate.of(2005, 01, 01);
        //List<Employee> list = empDao.findByHireDateBetweenOrderByHireDate(hireDate1, hireDate2);
        //List<Employee> list =empDao.findByManagerIdNull();
        
        //List<Employee> list = empDao.findByFirstName("Steven");
        
        //List<Employee> list = empDao.findByNameContaining("%an%");
        
        //List<Employee> list = empDao.findByDeptName("Sales");
        
        //List<Employee> list = empDao.findByCity("Seattle");
        
        //List<Employee> list = empDao.findByCountry("United Kingdom");
        
        List<Employee> list = empDao.findByCSH("United States of America", 7000D, hireDate1);
        
        list.forEach((x) -> log.info("x = {}" , x.toString()));
    }
}
