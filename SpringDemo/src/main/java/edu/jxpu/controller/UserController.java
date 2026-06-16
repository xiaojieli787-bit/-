package edu.jxpu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.jxpu.model.LoginUser;
import edu.jxpu.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录接口
	 */
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody Map<String, String> params, HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    String account = params.get("account");
	    String password = params.get("password");
	    
	    if(account == null || password == null || account.trim().isEmpty() || password.trim().isEmpty()) {
	        result.put("code", 400);
	        result.put("message", "账号和密码不能为空");
	        return result;
	    }
	    
	    LoginUser loginUser = userService.loginForUser(account, password);
	    if (loginUser != null) {
	        // 存储登录状态到Session
	        session.setAttribute("account", loginUser.getAccount());
	        session.setAttribute("role", loginUser.getRole());
	        
	        result.put("code", 200);
	        result.put("message", "登录成功");
	        result.put("data", loginUser);
	        return result;
	    }
	    
	    result.put("code", 401);
	    result.put("message", "账号或密码错误");
	    return result;
	}
	
	/**
	 * 退出登录接口
	 */
	@PostMapping("/logout")
	public Map<String, Object> logout(HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    session.invalidate();
	    result.put("code", 200);
	    result.put("message", "退出成功");
	    return result;
	}
	
	/**
	 * 获取当前登录用户信息
	 */
	@PostMapping("/info")
	public Map<String, Object> getCurrentUser(HttpSession session) {
	    Map<String, Object> result = new HashMap<>();
	    String account = (String) session.getAttribute("account");
	    String role = (String) session.getAttribute("role");
	    
	    if(account == null) {
	        result.put("code", 401);
	        result.put("message", "未登录");
	        return result;
	    }
	    
	    result.put("code", 200);
	    result.put("data", new HashMap<String, String>() {{
	        put("account", account);
	        put("role", role);
	    }});
	    return result;
	}
}