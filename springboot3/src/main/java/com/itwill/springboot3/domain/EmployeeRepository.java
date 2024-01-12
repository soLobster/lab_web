package com.itwill.springboot3.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    // JPA query method 작성 방법:
    
    // 엔터티에 필드명이 '_' (언더 스코어)가 있다면 인식하지 못한다
    // 따라서 @Column 애너테이션을 사용해서 name을 지정해서 테이블 컬럼명과 일치시키고 필드명에선 언더스코어를 빼야 JPA Query Method 사용 가능
    
    // 부서 번호가 일치하는 모든 직원들의 정보를 검색
    // SQL -> select * from employees where department_id = '?';
    
    // Departement 엔터티의 아이디를 이용해서 검색을 한다.
    List<Employee> findByDeptId(Integer id);
    
    // 부서 이름이 일치하는 (대소문자는 구분하지 않는) 모든 직원들의 정보를 검색
    // IgnoreCase 스팰링 주의..."대소문자 구분하지 않음"
    //List<Employee> findByDeptNameIgnoreCase(String name);
    
    // 성(lastName)이 일치하는 모든 직원들의 정보를 검색
    List<Employee> findByLastName(String lastName);
    
    // lastName에 특정 문자열이 포함되는 직원들의 정보
    List<Employee> findByLastNameContaining(String alphabet);
    
    // lastName에 대소문자 구분없이 특정 문자열이 포함되는 직원들의 정보
    List<Employee> findByLastNameContainingIgnoreCase(String alphabet);
    
    // lastName에 대소문자 구분없이 특정 문자열이 포함되고, 정렬 순서는 lastName 오름차순
    List<Employee> findByLastNameContainingIgnoreCaseOrderByLastName(String alphabet);
    
    // 급여(salary)가 어떤 값 초과인 직원들의 정보
    List<Employee> findBySalaryLessThan(Double salary);
    
    // 급여가 어떤 값 미만인 직원들의 정보
    List<Employee> findBySalaryGreaterThan(Double salary);
    
    // 급여가 어떤 범위 안에 있는 직원들의 정보
    List<Employee> findBySalaryBetween(Double salary1, Double salary2);
    
    // 입사 날짜 (hireDate)가 특정 날짜 이후인 직원들의 정보
    List<Employee> findByHireDateAfter(LocalDate hireDate);
    
    // 특정 날짜 이전인 직원들의 정보
    List<Employee> findByHireDateBefore(LocalDate hireDate);
    
    // 날짜 범위에 포함되는 직원들의 정보
    List<Employee> findByHireDateBetweenOrderByHireDate(LocalDate hireDate1, LocalDate hireDate2);
    
    // 매니저가 null인 직원들의 정보
    List<Employee> findByManagerIdNull();
    
    
    // JPQL(Java Persistence Query Language)
    // JPA에서 사용하는 객체지향 쿼리.
    // 테이블 이름과 테이블 컬럼으로 SQL을 작성하는 것이 아니라.
    // 엔티티 객체의 이름과 엔터티 필드 이름으로 쿼리를 작성하는 문법.
    // alias(별명)을 반드시 사용해야 함.
    
    // firstName 과 lastName이 일치하는 직원들의 정보
    // (도메인(Entity) 클래스의 이름 대소문자 구별함)
    //@Query("select e from Employee e where e.firstName = ?1")
    @Query("select e from Employee e " + 
    "where e.firstName = :first")
    List<Employee> findByFirstName(@Param("first") String firstName);
    
    // firstName or lastName에 특정 문자열을 포함하는 직원들의 정보. 대/소문자 구분 없이.
    @Query("select e from Employee e " + 
    "where upper(e.firstName) like upper(:key) or upper(e.lastName) like upper(:key)")
     List<Employee> findByNameContaining(@Param("key") String key);
    
    // Employee 테이블과 department 테이블을 조인한 후 Sales 부서의 직원들을 불러오는 쿼리 작성
    // 대소문자 구분 확실히 하자. 정의한 엔터티의 필드명과 동일하게 해야한다.
    @Query("select e from Employee e where e.dept.name = :key")
    List<Employee> findByDeptName(@Param("key") String deptname);
    
    // 특정 도시에 근무하는 직원들의 정보 검색
    @Query("select e from Employee e where e.dept.location.city = :key")
    List<Employee> findByCity(@Param("key") String city);
    
    // 특정 국가에 근무하는 직원들의 정보 검색
    @Query("select e from Employee e where e.dept.location.country.name = :key")
    List<Employee> findByCountry(@Param("key") String country);
    
    // 특정 국가에서 근무하는, 급여가 특정 금액 이상인, 입사일이 특정 날짜 이후인 직원 검색
    @Query("select e from Employee e where e.dept.location.country.name = ?1 and e.salary >= ?2 and e.hireDate >= ?3")
    List<Employee> findByCSH(String country, Double salary, LocalDate hireDate);
    
}
