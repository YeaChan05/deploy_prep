package com.yeachan.exp.service;

import com.yeachan.exp.domain.Member;
import com.yeachan.exp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * package :  com.yeachan.exp.service
 * fileName : MemberDetailsService
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    
    /**Member 정보를 Authorization과 함께 가져옴
     * @param email the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findOneWithAuthoritiesByEmail(email)
                .map(member -> createMember(email,member))
                .orElseThrow(()->new UsernameNotFoundException(email+" -> 데이터베이스에서 찾을 수 없습니다."));
    }
    
    /**
     * @param email
     * @param member
     * @return
     */
    private User createMember(String email, Member member) {
        if(!member.isActivated()){
            throw new RuntimeException(email+" -> 활성화되어 있지 않습니다.");
        }

        List<SimpleGrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .toList();
        return new User(member.getEmail(),member.getPassword(),grantedAuthorities);
    }
}
