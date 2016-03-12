package com.mbl.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.mbl.common.bean.Role;


public class AccountRoleVO {
	
	private String accountId;
	
	private List<Role> roles = new ArrayList<Role>();

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
