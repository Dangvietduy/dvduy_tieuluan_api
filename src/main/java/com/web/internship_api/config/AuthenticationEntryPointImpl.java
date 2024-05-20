package com.web.internship_api.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.internship_api.models.ResponseObject;

@Component
public class AuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint{
	@Override
	public void afterPropertiesSet() {
		setRealmName("Intership API");
		super.afterPropertiesSet();
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		ResponseObject responseObject = new ResponseObject(401, "UNAUTHORIZED", authException.getMessage());
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(responseObject);
		PrintWriter writer = response.getWriter();
		writer.println(json);
	}
}
