package com.slk.app.security.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.slk.app.entity.User;

/**
 * 
 * @author Sohan Kumawat
 *
 * Aug 4, 2016
 */
public class UserContext implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6084609991527296369L;
	private  String userId;
    private String emailId;
    
   

    private UserContext(){
    	
    }
    
    private UserContext(User user, List<SimpleGrantedAuthority> authorities) {
        this.userId = String.valueOf(user.getUserId());
        this.emailId=user.getLoginId();
    }
    
    public static UserContext create(User user, List<SimpleGrantedAuthority> authorities) {
        if (user.getUserId()== 0) throw new IllegalArgumentException("Configuration is not correct ");
        return new UserContext(user, authorities);
    }


   /* public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }*/

	public String getUserId() {
		return userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
