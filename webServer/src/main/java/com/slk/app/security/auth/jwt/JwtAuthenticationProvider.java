package com.slk.app.security.auth.jwt;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slk.app.security.auth.JwtAuthenticationToken;
import com.slk.app.security.config.JwtSettings;
import com.slk.app.security.model.UserContext;
import com.slk.app.security.model.token.JwtToken;
import com.slk.app.security.model.token.RawAccessJwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * An {@link AuthenticationProvider} implementation that will use provided
 * instance of {@link JwtToken} to perform authentication.
 * 
 * @author 
 *
 * Aug 5, 2016
 */
@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtSettings jwtSettings;
    
    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        String jsonString = jwsClaims.getBody().get("profileScope", String.class);
        List<String> scopes = jwsClaims.getBody().get("role", List.class);
        
        ObjectMapper mapper= new ObjectMapper();
       UserContext userContext;
	try {
		userContext = mapper.readValue(jsonString, UserContext.class);
	} catch (Exception e) {
		throw new AuthenticationCredentialsNotFoundException("Bad Credential Exception");
		
	}
      
       
        List<GrantedAuthority> authorities = scopes.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
      // as its is not of use right now just creating dummy user and adding . but definately need to change 
     //   UserContext context = UserContext.create(userContext, authorities);
        
        return new JwtAuthenticationToken(userContext, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
