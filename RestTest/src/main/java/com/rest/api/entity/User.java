package com.rest.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder    // builder 사용
@Entity     // jpa entity 선언
@Getter     // getter 자동 생성
@NoArgsConstructor  // 인자 없는 생성자 자동 생성
@AllArgsConstructor // 인자를 모두 갖춘 생성자 자동 생성
@Table(name = "user")   // 'user' 테이블과 매핑됨을 명시
public class User {
    @Id // primaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 전략을 DB에 위임
    private long msrl;
    @Column(nullable = false, unique = true, length = 30)
    private String uid;
    @Column(nullable = false, length = 100)
    private String name;
}
