package com.cs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the user_info database table.
 * 
 */
@Entity
@Table(name="user_info")
@NamedQuery(name="UserInfo.findAll", query="SELECT u FROM UserInfo u")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(name="open_id")
	private String openId;

	private Integer sex;

	@Column(name="union_id")
	private String unionId;

	private String username;

	public UserInfo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setCreateDate(Date date) {
		// TODO Auto-generated method stub
		
	}

}