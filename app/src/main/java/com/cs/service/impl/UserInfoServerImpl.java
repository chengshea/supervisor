package com.cs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cs.model.UserInfo;
import com.cs.repository.UserInfoRepository;
import com.cs.repository.custom.CustomUserInfo;
import com.cs.service.UserInfoService;


@Service
@Transactional
public class UserInfoServerImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private  CustomUserInfo customUserInfo;
	
	@Override
	public Long save(UserInfo user) {
		UserInfo info = userInfoRepository.save(user);
		return info.getId();
	}

	@Override
	public Page<UserInfo> getpage( int page, int size) {
		 Sort sort = new Sort(Sort.Direction.ASC,"birthday");  
	        Pageable pageable = new PageRequest(page,size,sort); 
		return customUserInfo.findAll(pageable);
	}
}
