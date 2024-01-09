package com.itwill.springboot1.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "answers")
public class Answer {
    
    @Id
    @GeneratedValue(generator = "answer_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "answer_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk
    
    @ManyToOne
    private Question question; // fk
    
    @Basic(optional = false)
    private String answer;
}
