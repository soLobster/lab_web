package com.itwill.springboot1.domain;

import java.math.BigDecimal;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // JPA의 Entity 클래스
@Table(name = "USERS") //데이터베이스의 테이블 이름 변경/설정 하는 애너테이션
// 엔터티 클래스가 상속하는 상위 클래스에는 (BaseTimeEntity) @MappedSuperclass 애너테이션을 사용해야 함.
public class User extends BaseTimeEntity {
    
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // MySql의 Autoincrement와 비슷한 기능. Oracle as identify 
    private Long id;
    
    @NaturalId // Unique 제약 조건을 준것
    @Basic(optional = false) // Database의 기본 타입으로 매핑. optional false => NOT NULL 제약 조건과 똑같음
    private String username;
    
    @Enumerated(EnumType.STRING) // ENUM의 타입을 스트링 -> VARCHAR2로 생성
    // -> @Enumerated(EnumType.ordinal) 기본값. 기본값인 경우에는 생략 가능.
    // -> Ordinal: 숫지 타입 컬럼으로 매핑.
    // -> String: 문자열 타입 컬럼으로 매핑.
    private Gender gender; 
    // int i = Gender.FEMALE.ordinal(); 
    // enum의 정의된 순서대로 숫자를 리턴한다.
    
    @Column(length = 1000)
    // length => DDL 자동 실행할 때만, 테이블 컬럼의 문자열 길이 설정
    private String memo;
    
    @Column(name = "SAL", precision = 4, scale = 2)
    // -> name: (엔터티 객체의 필드 이름과 테이블 컬럼 이름이 다를 때) 테이블의 컬럼 이름을 설정.
    // precision, scale: DDL 자동 실행할 때. 오라클에서는 Number(Precision, scale) 매핑 됨
    private BigDecimal salary;
    
    // @Embedded (생략 가능) // @Embeddable 애너테이션을 사용한 객체를 포함.
    private Address address;
}
