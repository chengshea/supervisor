package com.cs.service;

import org.springframework.data.domain.Page;

import com.cs.model.UserInfo;

public interface UserInfoService {

	  Long  save(UserInfo user);
	  
	  Page<UserInfo> getpage( int page, int size);
}
