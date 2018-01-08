package com.cs.repository.custom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.cs.model.UserInfo;

@Repository
public interface CustomUserInfo extends JpaRepository<UserInfo, Long>,JpaSpecificationExecutor<UserInfo> {

}
