package com.itwill.springboot2.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "dept")
public class Department {
    
    @Id
    @Column(name = "DEPTNO")
    private Integer id;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    
    private String dname;
    
    private String loc;
}
