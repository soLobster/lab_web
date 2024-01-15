package com.itwill.springboot4.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)  // default -> Public
// Protected -> 다른 패키지에 소속된 클래스(상속은 제외) ( 접근 불가 ) 
// Private -> 모든 외부 클래스 ( 접근 불가 ) 
// ACCESS 레벨을 지정할 수 있다. -> 외부에 노출 시킬 필요가 없다. Private
@Builder // @AllArgsConstructor가 있어야 사용 가능
@Getter
@ToString(callSuper = true) // 기본 값 false
@EqualsAndHashCode(callSuper = true)
@Table(name = "POSTS")
public class Post extends BaseTimeEntity{

    @Id // PK (sequence 값)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    // 테이블 생성시 generated as identity.
    private Long id;
    
    @Basic(optional = false) 
    // @Basic(optional = false) -> NOT NULL임을 표기
    private String title;
    
    @Basic(optional = false)
    private String content;
    
    @Basic(optional = false)
    private String author;
    
    // update에 필요한 method
    public Post update(String title, String content) {
        this.title = title;
        this.content = content;
        // 자기 자신을 돌려준다.
        return this;
    };
}
