package edu.jxpu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jxpu.dao.LoginDao;
import edu.jxpu.model.LoginUser;

@Service("userService")
public class UserService {
	@Autowired
	private LoginDao loginDao;
	
	/**
	 * @param account  用户账号
	 * @param password 用户密码
	 * @return boolean 返回登录结果，true为成功，false为失败
	 */
	public boolean login(String account, String password) {
		// MyBatis返回匹配的记录数，判断是否大于0
		boolean isValid = loginDao.loginCheck(account, password) > 0;
		if (isValid) {
			System.out.println("login---登录成功！账号：" + account);
		} else {
			System.out.println("login---登录失败！账号或密码错误：" + account);
		}
		return isValid;
	}
	
	/**
	 * @param account  用户账号
	 * @param password 用户密码
	 * @return LoginUser 返回LoginUser对象
	 */
	public LoginUser loginForUser(String account, String password) {
		LoginUser loginUser = loginDao.loginForUser(account, password);
		if (loginUser != null) {
			System.out.println("loginForUser---登录成功！账号：" + account);
		} else {
			System.out.println("loginForUser---登录失败！账号或密码错误：" + account);
		}
		return loginUser;
	}
}