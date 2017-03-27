package com.slk.app.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * JwtTokenNotValid
 * 
 * @author Sohan Kumawat
 *
 * Aug 17, 2016
 */
public class InvalidJwtToken extends AuthenticationException {
    
	
	private static final long serialVersionUID = -294671188037098603L;
	public static final String REFRESH_TOKEN_TYPE="refreshtoken";
	public static final String MAIN_TOKEN_TYPE="maintoken";
	
	public InvalidJwtToken(String tokenType,String msg) {
	
		super(msg);
		this.tokenType=tokenType;
		// TODO Auto-generated constructor stub
	}
    private String tokenType;
	
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
	
	
}
