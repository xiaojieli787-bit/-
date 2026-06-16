package edu.jxpu.model;

/**
 * 登录用户信息
 * @author Administrator
 *
 */
public class LoginUser {
    private String account;
    private String role;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginUser [account=" + account + ", role=" + role + "]";
	}

    
}
