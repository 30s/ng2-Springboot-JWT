package com.slk.app.security.auth.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.slk.app.entity.User;
import com.slk.app.security.model.UserContext;
import com.slk.app.service.UserService;

/**
 * 
 * @author Sohan Kumawat
 *
 * Aug 3, 2016
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
    
    private final UserService userService;

    @Autowired
    public LoginAuthenticationProvider(final UserService userService) {
        this.userService = userService;
       
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        if(password.isEmpty()){
        	throw new BadCredentialsException("Password is null or blank");
        }
        User user = userService.getUserByLoginId(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        Md5PasswordEncoder passwordEncoder= new Md5PasswordEncoder();
        String hashPassword=passwordEncoder.encodePassword(password, null);
       // String encodePassword=MD5Encoder.encode(password.getBytes());
        if (!hashPassword.equals(user.getPassword())) {
            throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
        }

        //just by passing the role code for now
      //  if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
        
      /* List<GrantedAuthority> authorities = user.getUser_roles().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
               .collect(Collectors.toList());*/
        
       List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
        
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserContext userContext = UserContext.create(user, authorities);
        
        return new UsernamePasswordAuthenticationToken(userContext, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
