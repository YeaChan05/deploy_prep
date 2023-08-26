package com.yeachan.exp.api;

import com.yeachan.exp.domain.Member;
import com.yeachan.exp.dto.LoginDto;
import com.yeachan.exp.dto.MemberDto;
import com.yeachan.exp.dto.TokenDto;
import com.yeachan.exp.jwt.JwtFilter;
import com.yeachan.exp.jwt.TokenProvider;
import com.yeachan.exp.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * package :  com.yeachan.exp.api
 * fileName : AuthController
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken
                =new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        
        String jwt=tokenProvider.createToken(authenticate);
        
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody MemberDto memberDto){
        Member member = memberService.signUp(memberDto);
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(member);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberDto> getMyMemberInfo(){
        Optional<Member> myMemberWithAuthorities = memberService.getMyMemberWithAuthorities();
        return myMemberWithAuthorities.map(member -> ResponseEntity.ok(member.toResponseDto())).orElseGet(()->ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
    
    @GetMapping("/member/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberDto> getMemberInfo(@PathVariable String email){
        Optional<Member> memberWithAuthorities = memberService.getMemberWithAuthorities(email);
        return memberWithAuthorities.map(member -> ResponseEntity.ok(member.toResponseDto())).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }
}
