package com.tiangou.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.pojo.TbUser;
import com.tiangou.sso.service.RegisterService;

@Controller
@RequestMapping("/user")
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@RequestMapping("/check/{param}/{type}")
	@ResponseBody
	public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {
		try {
			TiangouResult result = registerService.checkData(param, type);
			if (StringUtils.isNotBlank(callback)) {
				//请求为jsonp调用，需要支持
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
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody
	public TiangouResult register(TbUser user) {
		try {
			String password = user.getPassword();
			user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			TiangouResult result = registerService.register(user);
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			//return TiangouResult.build(500, ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	
}
