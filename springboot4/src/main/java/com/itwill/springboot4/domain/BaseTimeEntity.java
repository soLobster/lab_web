package com.itwill.springboot4.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

// BaseTimeEntity를 상속 받는 모든 엔터티들이 공통으로 갖는 시간을 정의할 예정
@NoArgsConstructor // 기본 생성자.
@Getter // getter 메서드
@ToString
@EqualsAndHashCode // equals(), canEqaul(), hashCode()
@MappedSuperclass // 다른 엔터티 클래스의 상위 클래스로 사용하기 위해서 반드시 있어야한다.
@EntityListeners(AuditingEntityListener.class)
// -> 엔터티 생성시간, 최종수정시간 등을 자동으로 DB에 저장하기 위해서 사용.
public class BaseTimeEntity {
 // 카멜 표기법으로 작성시 jpa에서 자동으로 언더스코어 형식으로 치환 됨
    
    @CreatedDate // Entity 생성 시간을 저장하는 필드.
    private LocalDateTime createdTime; 

    @LastModifiedDate // Entity의 최종 수정 시간을 저장하는 필드.
    private LocalDateTime modifiedTime;
    
    public LocalDate changeDateType(LocalDateTime time) {
        return createdTime.toLocalDate();
    }
    
    public LocalDateTime updateCreatedTime(LocalDateTime time) {
        return this.createdTime;
    }
}
