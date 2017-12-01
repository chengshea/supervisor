package com.cs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cs.model.UserInfo;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{

}
