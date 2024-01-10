package com.itwill.springboot2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//참조하는 도메인의 클래스명과 PK의 데이터 타입(Wrapper)
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
