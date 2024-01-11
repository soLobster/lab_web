package com.itwill.springboot3.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "DEPARTMENTS")
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID")
    private Integer id;
    
    @Column(name = "DEPARTMENT_NAME")
    private String name;
    
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "EMPLOYEE_ID")
    private Employee manager;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "dept" , fetch = FetchType.LAZY)
    private List<Employee> employees;
    
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;
}
