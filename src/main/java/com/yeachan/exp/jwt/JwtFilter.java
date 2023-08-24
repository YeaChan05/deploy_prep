package com.yeachan.exp.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * package :  com.yeachan.exp.domain.jwt
 * fileName : JwtFilter
 * author :  ShinYeaChan
 * date : 2023-08-24
 */
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    public static String AUTHORIZATION_HEADER="Authorization";
    private final TokenProvider tokenProvider;
    
    
    /**SecurityContext에 저장하기 이전 filter 수행
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                 to for further processing
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        String jwt=resolveToken(httpServletRequest);
        String requestURI=httpServletRequest.getRequestURI();
        if(StringUtils.hasText(jwt)&& tokenProvider.validateToken(jwt)){
            Authentication authentication= tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        chain.doFilter(request,response);
    }
    
    /**HTTP request header에서 토큰 정보를 가져옴
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request){
        String bearerToken=request.getHeader(AUTHORIZATION_HEADER);
        
        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
