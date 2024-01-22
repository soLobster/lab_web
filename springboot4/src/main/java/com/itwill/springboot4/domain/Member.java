package com.itwill.springboot4.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false) // 명시한 것 들만 포함 시키겠다. , 부모 클래스는 호출 하지 않겠다.
@Table(name = "MEMBERS")
public class Member extends BaseTimeEntity {
    
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include // EqualsAndHashcode에 포함 시키겠다. (onlyExplicitlyIncluded) Username 필드 만으로 equals 비교를 하기 위해서.
    @NaturalId // Unique
    @Basic(optional = false)
    @Column(updatable = false) // update 쿼리의 SET 절에서 제외.
    private String username;
    
    @Basic(optional = false)
    private String password;
    
    @Basic(optional = false)
    private String email;
    
    @Builder.Default // Builder에서 null이 아닌 Set<> 객체를 생성하기 위해서.
    @ToString.Exclude
    @ElementCollection(fetch = FetchType.LAZY) // 연관된 별도의 테이블을 사용.
    @Enumerated(EnumType.STRING) // ENUM과 관련된 애너테이션 , DB 테이블에 저장할 때 상수 이름(문자열)을 사용하겠다.
    private Set<MemberRole> roles = new HashSet<>(); // roles를 저장하는 집합. 
    // 엔터티의 이름과 필드의 이름을 합쳐서 entity_field 형태의 테이블로 insert 된다.
    // set 형식이라 memeber_id 를 자동으로 JPA가 찾아준다. 
    public Member addRole(MemberRole role) {
        roles.add(role);
        return this;
    }
    
    public Member clearRoles() {
        roles.clear(); // 모든 앨리먼트를 지운다.
        return this;
    }
}
