package com.itwill.springboot3.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "JOBS")
public class Job {

    @Id
    @Column(name = "JOB_ID")
    private String id;
    
    @Column(name = "JOB_TITLE")
    private String title;
    
    private Long min_salary;
    
    private Long max_salary;
}
