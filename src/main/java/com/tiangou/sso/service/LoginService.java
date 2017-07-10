package com.tiangou.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.TiangouResult;

@Service
public interface LoginService {
	public TiangouResult login(String username, String password, HttpServletRequest request,
			HttpServletResponse response);
	
	public TiangouResult getUserByToken(String token) ;
}
