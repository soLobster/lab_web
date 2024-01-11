package com.itwill.springboot3.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Integer id;
    
    private String first_name;
    
    private String last_name;
    
    private String email;
    
    private String phone_number;
    
    private LocalDate hire_date;
    
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID")
    private Job job;
    
    private Double salary;
    
    private Double commission_pct;
    
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id" , referencedColumnName = "EMPLOYEE_ID")
    private Employee manager;
    
    @ManyToOne // DEPARTMENT 테이블을 참조한다
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department dept;
}
