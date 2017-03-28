package com.slk.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slk.app.common.model.ErrorCode;
import com.slk.app.common.model.ErrorResponse;
import com.slk.app.security.exceptions.InvalidJwtToken;

/**
 * 
 * @author Sohan Kumawat
 *
 * Aug 4, 2016
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		if(ex instanceof InvalidJwtToken && InvalidJwtToken.REFRESH_TOKEN_TYPE.equals(((InvalidJwtToken) ex).getTokenType())){
			 ObjectMapper mapper= new ObjectMapper();
 			mapper.writeValue(response.getWriter(), ErrorResponse.of("refresh token has expired", ErrorCode.REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
 		
		}
		
	}
}
