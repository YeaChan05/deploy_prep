package com.yeachan.exp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long memberId;
    
    @Column(name = "member_name", length = 50)
    private String memberName;
    
    @Column(name = "email", unique = true)
    @Email
    private String email;
    
    @Column(name = "password", length = 100)
    private String password;
    
    @Column(name = "activated")
    private boolean activated;
    
    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id",referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name",referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}