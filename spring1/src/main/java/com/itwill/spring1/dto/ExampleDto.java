package com.itwill.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
//@Data = @ToString + @EqualsAndHashCode + @Getter + @Setter + @RequiredArgsConstructor
//@NoArgsContructor: 아규먼트를 갖지 않는 생성자. 기본 생성자.
//@AllArgsConstructor: 모든 필드들을 아규먼트로 갖는 생성자.
//@RequiredArgsConstructor: 파이널 필드들만 아규먼트로 갖는 생성자.
@Builder // Builder 패턴 내부 클래스와 static 매서드를 자동으로 만들어줌.
public class ExampleDto {
    
    private String username; // 요청 파라미터 이름과 같게 필드 이름을 선언.
    private int age; // 요청 파라미터 이름과 같게 필드 이름을 선언.
    
    
    
}
