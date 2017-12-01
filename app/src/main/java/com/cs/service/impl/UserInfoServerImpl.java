package com.cs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.model.UserInfo;
import com.cs.repository.UserInfoRepository;
import com.cs.service.UserInfoService;


@Service
@Transactional
public class UserInfoServerImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public Long save(UserInfo user) {
		UserInfo info = userInfoRepository.save(user);
		return info.getId();
	}

}
