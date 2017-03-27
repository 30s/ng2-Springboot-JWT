package com.slk.app.security.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.slk.app.entity.User;
import com.slk.app.security.auth.jwt.extractor.TokenExtractor;
import com.slk.app.security.auth.jwt.verifier.TokenVerifier;
import com.slk.app.security.config.JwtSettings;
import com.slk.app.security.config.WebSecurityConfig;
import com.slk.app.security.exceptions.InvalidJwtToken;
import com.slk.app.security.exceptions.JwtExpiredTokenException;
import com.slk.app.security.model.UserContext;
import com.slk.app.security.model.token.JwtToken;
import com.slk.app.security.model.token.JwtTokenFactory;
import com.slk.app.security.model.token.RawAccessJwtToken;
import com.slk.app.security.model.token.RefreshToken;
import com.slk.app.service.UserService;

/**
 * RefreshTokenEndpoint
 * 
 * @author Sohan Kumawat
 *
 * Aug 17, 2016
 */
@RestController
public class RefreshTokenEndpoint {
    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;
    
    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,AuthenticationException {
        String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken=null;
       try{
         refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken(InvalidJwtToken.REFRESH_TOKEN_TYPE,"refresh token is invalid"));
       }catch(JwtExpiredTokenException e){
    	    throw new InvalidJwtToken(InvalidJwtToken.REFRESH_TOKEN_TYPE,"refresh token is invalid");
       }
        String jti = refreshToken.getJti();
        if (tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken(InvalidJwtToken.REFRESH_TOKEN_TYPE,"refresh token is invalid");
        }

        String subject = refreshToken.getSubject();
        User user = userService.getUserByLoginId(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));
       
        // bypassing role based configuration for now .
        List<SimpleGrantedAuthority> authorities= new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
       /* if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());*/

        UserContext userContext = UserContext.create(user, authorities);

        return tokenFactory.createAccessJwtToken(userContext);
    }
}
