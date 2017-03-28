package com.slk.app.security.auth.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slk.app.common.model.ErrorCode;
import com.slk.app.common.model.ErrorResponse;
import com.slk.app.security.auth.JwtAuthenticationToken;
import com.slk.app.security.auth.jwt.extractor.TokenExtractor;
import com.slk.app.security.config.WebSecurityConfig;
import com.slk.app.security.exceptions.InvalidJwtToken;
import com.slk.app.security.exceptions.JwtExpiredTokenException;
import com.slk.app.security.model.token.RawAccessJwtToken;


/**
 * Performs validation of provided JWT Token.
 * 
 * @author Sohan Kumawat
 *
 * Aug 5, 2016
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;
    ObjectMapper mapper=null;
    @Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler, 
            TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
       
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if(failed instanceof JwtExpiredTokenException){
             mapper= new ObjectMapper();
    		mapper.writeValue(response.getWriter(), ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
    		
        }else if(failed instanceof InvalidJwtToken){
        	mapper= new ObjectMapper();
			mapper.writeValue(response.getWriter(), ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
		
        }
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
