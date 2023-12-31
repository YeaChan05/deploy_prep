package com.yeachan.exp.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * package :  com.yeachan.exp.domain.jwt
 * fileName : TokenProvider
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@Component
@Slf4j
public class TokenProvider implements InitializingBean {
    private static final String AUTHORITIES_KEY ="auth";
    private final String secret;
    private final long tokenValidityInMillisecond;
    private Key key;
    public TokenProvider(
            @Value("${jwt.secret}")String secret,
            @Value("${jwt.token-validity-in-second}")long tokenValidityInMillisecond) {
        this.secret = secret;
        this.tokenValidityInMillisecond=tokenValidityInMillisecond*1000;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[]  keyBytes= Decoders.BASE64.decode(secret);
        this.key= Keys.hmacShaKeyFor(keyBytes);
    }
    
    /**토큰 생성
     * @param authentication
     * @return
     */
    public String createToken(Authentication authentication){
        String authorities=authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        
        long now=(new Date()).getTime();
        Date validity=new Date(now+this.tokenValidityInMillisecond);
        
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY,authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }
    
    /**인증 객체 반환
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token){
        Claims claims=Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        Collection<?extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new).toList();
    
        User principal=new User(claims.getSubject(),"",authorities);
        
        return new UsernamePasswordAuthenticationToken(principal,token,authorities);
    }
    
    /**토큰 검증
     * @param token
     * @return
     */
    public boolean validateToken(String token){
        try{
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (SecurityException| MalformedJwtException e){
            log.info("잘못된 JWT 서명입니다.");
        }catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰입니다.");
        }catch (UnsupportedJwtException e){
            log.info("지원되지 않는 JWT 서명입니다.");
        }catch (IllegalArgumentException e){
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
