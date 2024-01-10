package com.itwill.springboot2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dept")
public class Department {
    
    @Id
    @Column(name = "DEPTNO")
    private Integer id;
    
    private String dname;
    
    private String loc;
}
