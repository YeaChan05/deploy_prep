package com.yeachan.exp.service;

import com.yeachan.exp.domain.Authority;
import com.yeachan.exp.domain.Member;
import com.yeachan.exp.dto.MemberDto;
import com.yeachan.exp.repository.MemberRepository;
import com.yeachan.exp.service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * package :  com.yeachan.exp.service
 * fileName : MemberService
 * author :  ShinYeaChan
 * date : 2023-08-25
 */
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**회원가입을 위한 객체 저장 과정
     * @param memberDto
     * @return
     */
    @Transactional
    public Member signUp(MemberDto memberDto){
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null)!=null)
            throw new RuntimeException("이미 가입되어 있는 사용자입니다.");
    
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        Member member=Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .memberName(memberDto.getMemberName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        return memberRepository.save(member);
    }
    
    /**email을 통한 member 객체 검색
     * @param email
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<Member> getMemberWithAuthorities(String email){
        return memberRepository.findOneWithAuthoritiesByEmail(email);
    }
    
    /**현재 SecurityContextHolder에 있는 사용자의 정보를 기준으로 DB에서 검색
     * @return
     */
    @Transactional(readOnly = true)
    public Optional<Member> getMyMemberWithAuthorities(){
        return SecurityUtils.getCurrentEmail().flatMap(memberRepository::findOneWithAuthoritiesByEmail);
    }
}
