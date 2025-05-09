package com.example.aiweb.entity;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String membername;
    private String password;
    private String email;

    // 기본 생성자
    public Member() {

    }

    // enum으로 역할(Role) 정의
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;


    // 생성자
    public Member(String membername, String password, String email) {
        this.membername = membername;
        this.password = password;
        this.email = email;
    }

    // getter, setter (우클릭 > Generate > Getter and Setter)
    public Long getId() { return id; }

    public String getMembername() { return membername; }
    public void setUsername(String username) { this.membername = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
