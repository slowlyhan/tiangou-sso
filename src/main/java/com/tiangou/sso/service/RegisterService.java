package com.tiangou.sso.service;

import org.springframework.stereotype.Service;

import com.tiangou.common.pojo.TiangouResult;
import com.tiangou.pojo.TbUser;

@Service
public interface RegisterService {
	public TiangouResult checkData(String param, int type);
	public TiangouResult register(TbUser user);
}
