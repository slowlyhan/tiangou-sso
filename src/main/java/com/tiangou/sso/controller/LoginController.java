package com.tiangou.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.sso.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	@ResponseBody
	public TiangouResult login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response) {
		try {
			TiangouResult result = loginService.login(username, password, request, response);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
		
		@RequestMapping("/user/token/{token}")
		@ResponseBody
		public Object getUserByToken(@PathVariable String token, String callback) {
			try {
				TiangouResult result = loginService.getUserByToken(token);
				//支持jsonp调用
				if (StringUtils.isNotBlank(callback)) {
					MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
					mappingJacksonValue.setJsonpFunction(callback);
					return mappingJacksonValue;
				}
				return result;
				
			} catch (Exception e) {
				e.printStackTrace();
				//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
				return null;
			}
		}
	
}